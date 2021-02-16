package iuliia.movies.domain.movies;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MovieRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert movieInsert;
    private final SqlUpdate movieUpdate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.movieInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("movie")
                .usingGeneratedKeyColumns("id");
        this.movieUpdate = new SqlUpdate(jdbcTemplate.getDataSource(),
                "UPDATE movie " +
                        "SET title = :title, director = :director, country = :country," +
                        "length = :length, year = :year, premier = :premier " +
                        "WHERE id = :id",
                new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
                        Types.INTEGER, Types.DATE, Types.BIGINT});
    }

    public List<Movie> findMoviesByActorId(Long actorId) {
        return jdbcTemplate.query("SELECT * " +
                "from movie INNER JOIN movie_cast ON (movie.id = movie_id) " +
                "WHERE cast_id = ?", MovieRepository::mapRow, actorId);
    }

    public List<Movie> findAll() {
        return jdbcTemplate.query("SELECT * from movie ", MovieRepository::mapRow);
    }

    public Optional<Movie> findById(Long id) {
        try {
            Movie movie = jdbcTemplate.queryForObject("SELECT * FROM movie WHERE id = ?",
                    MovieRepository::mapRow,
                    id);
            return Optional.ofNullable(movie);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Movie save(Movie movie) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", movie.getTitle());
        params.put("director", movie.getDirector());
        params.put("country", movie.getCountry());
        params.put("length", movie.getLength());
        params.put("year", movie.getYear());
        var premier = movie.getPremier();
        params.put("premier", (premier != null) ? Date.valueOf(premier) : null);
        if (movie.getId() == null) {
            var id = movieInsert.executeAndReturnKey(params);
            movie.setId(id.longValue());
        } else {
            params.put("id", movie.getId());
            movieUpdate.updateByNamedParam(params);
        }
        return movie;
    }


    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM movie WHERE id = ?", id);
    }

    private static Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        var movie = new Movie();
        movie.setId(rs.getLong("id"));
        movie.setTitle(rs.getString("title"));
        movie.setCountry(rs.getString("country"));
        movie.setDirector(rs.getString("director"));
        movie.setLength(rs.getInt("length"));
        movie.setYear(rs.getInt("year"));
        var premier = rs.getDate("premier");
        if (premier != null) {
            movie.setPremier(premier.toLocalDate());
        }
        return movie;
    }

    public void addActorToMovie(Long movieId, Long actorId) {
        jdbcTemplate.update("INSERT INTO movie_cast (movie_id, cast_id) VALUES(?, ?)", movieId, actorId);
    }

    public void deleteMovieCast(long id) {
        jdbcTemplate.update("DELETE FROM movie_cast WHERE movie_id = ?", id);
    }
}
