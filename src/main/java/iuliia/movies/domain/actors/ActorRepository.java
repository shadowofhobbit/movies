package iuliia.movies.domain.actors;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
public class ActorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Actor> findActorsByMovieId(Long movieId) {
        return entityManager
                .createQuery("select a from Actor a join fetch a.movies m where m.id = ?1",
                        Actor.class)
                .setParameter(1, movieId)
                .getResultList();
    }

    public Actor save(Actor actor) {
        if (actor.getId() == null) {
            entityManager.persist(actor);
            return actor;
        } else {
            return entityManager.merge(actor);
        }
    }

    public Iterable<Actor> findAll() {
        return entityManager
                .createQuery("select a from Actor a", Actor.class)
                .getResultList();
    }

    public Optional<Actor> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Actor.class, id));
    }

    public void deleteById(Long id) {
        var deletedCount = entityManager
                .createQuery("delete from Actor a where a.id= :id")
                .setParameter("id", id)
                .executeUpdate();
        if (deletedCount != 1) {
            log.error("Error deleting actor with id " + id);
        }
    }
}
