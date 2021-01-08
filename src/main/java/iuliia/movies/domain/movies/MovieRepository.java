package iuliia.movies.domain.movies;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Movie> findMoviesByActorId(Long actorId) {
        return entityManager
                .createQuery("select m from Movie m join m.cast a on a.id = ?1", Movie.class)
                .setParameter(1, actorId)
                .getResultList();
    }


    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            entityManager.persist(movie);
        } else {
            entityManager.merge(movie);
        }
        return movie;
    }

    public List<Movie> findAll() {
        return entityManager
                .createQuery("select m from Movie m", Movie.class)
                .getResultList();
    }

    public Optional<Movie> findById(Long id) {
        var movie = entityManager.find(Movie.class, id);
        return Optional.ofNullable(movie);
    }

    public void deleteById(Long id) {
        entityManager
                .createQuery("delete from Movie m where m.id = :mid")
                .setParameter("mid", id)
                .executeUpdate();
    }
}
