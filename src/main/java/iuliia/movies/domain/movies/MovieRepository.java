package iuliia.movies.domain.movies;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieRepository {
    private final SessionFactory sessionFactory;

    public List<Movie> findMoviesByActorId(Long actorId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select m from Movie m join m.cast a on a.id = ?1", Movie.class)
                .setParameter(1, actorId)
                .list();
    }

    public Movie getOne(Long movieId) {
        return sessionFactory.getCurrentSession()
                .get(Movie.class, movieId);
    }

    public Movie save(Movie movie) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(movie);
        return movie;
    }

    public List<Movie> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Movie m", Movie.class)
                .list();
    }

    public Optional<Movie> findById(Long id) {
        var movie = sessionFactory.getCurrentSession()
                .find(Movie.class, id);
        return Optional.ofNullable(movie);
    }

    public void deleteById(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from Movie m where id = :mid")
                .setParameter("mid", id)
                .executeUpdate();
    }
}
