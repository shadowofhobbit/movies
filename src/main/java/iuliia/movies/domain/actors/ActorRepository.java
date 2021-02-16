package iuliia.movies.domain.actors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ActorRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert actorInsert;

    @Autowired
    public ActorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.actorInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("actor")
                .usingGeneratedKeyColumns("id");
    }

    public List<Actor> findActorsByMovieId(Long movieId) {
        return jdbcTemplate.query("SELECT *" +
                        "FROM actor INNER JOIN movie_cast ON (cast_id = actor.id)" +
                        "WHERE movie_id = ?",
                this::mapRow, movieId);
    }

    public void deleteActorFromMovies(long id) {
        jdbcTemplate.update("DELETE FROM movie_cast WHERE cast_id = ?", id);
    }

    public Optional<Actor> findById(Long id) {
        try {
            var actor = jdbcTemplate.queryForObject("SELECT * FROM actor WHERE id = ?",
                    this::mapRow,
                    id);
            return Optional.ofNullable(actor);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public Iterable<Actor> findAll() {
        return jdbcTemplate.query("SELECT * FROM actor", this::mapRow);
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM actor WHERE id = ?", id);
    }

    public Actor save(Actor actor) {
        if (actor.getId() == null) {
            Map<String, Object> params = new HashMap<>();
            params.put("first_name", actor.getFirstName());
            params.put("last_name", actor.getLastName());
            params.put("middle_name", actor.getMiddleName());
            var birthday = actor.getBirthday();
            if (birthday != null) {
                params.put("birthday", Date.valueOf(birthday));
            }
            var id = actorInsert.executeAndReturnKey(params);
            actor.setId(id.longValue());

        } else {
            jdbcTemplate.update("UPDATE actor SET first_name = ?, last_name = ?, middle_name = ?, birthday = ? " +
                            "WHERE id = ?",
                    actor.getFirstName(), actor.getLastName(), actor.getMiddleName(), actor.getBirthday(), actor.getId());
        }
        return actor;
    }

    private Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
        var actor = new Actor();
        actor.setId(rs.getLong("id"));
        actor.setFirstName(rs.getString("first_name"));
        actor.setMiddleName(rs.getString("middle_name"));
        actor.setLastName(rs.getString("last_name"));
        var birthday = rs.getDate("birthday");
        if (birthday != null) {
            actor.setBirthday(birthday.toLocalDate());
        }
        return actor;
    }

}
