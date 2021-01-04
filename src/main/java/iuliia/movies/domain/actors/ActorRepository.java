package iuliia.movies.domain.actors;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ActorRepository {
    private final SessionFactory sessionFactory;

    public List<Actor> findActorsByMovieId(Long movieId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select a from Actor a join fetch a.movies m where m.id = ?1",
                        Actor.class)
                .setParameter(1, movieId)
                .list();
    }

    public Actor save(Actor actor) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(actor);
        return actor;
    }

    public Iterable<Actor> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Actor a", Actor.class)
                .list();
    }

    public Optional<Actor> findById(Long id) {
        return Optional.ofNullable(
                sessionFactory.getCurrentSession()
                        .find(Actor.class, id));
    }

    public void deleteById(Long id) {
        var deletedCount = sessionFactory.getCurrentSession()
                .createQuery("delete from Actor a where id= :id")
                .setParameter("id", id)
                .executeUpdate();
        if (deletedCount != 1) {
            log.error("Error deleting actor with id " + id);
        }
    }
}
