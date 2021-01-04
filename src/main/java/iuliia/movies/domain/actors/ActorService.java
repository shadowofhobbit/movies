package iuliia.movies.domain.actors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;

    public Actor create(Actor actor) {
        return actorRepository.save(actor);
    }

    @Transactional(readOnly = true)
    public Iterable<Actor> getAll() {
        return actorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Actor getById(Long id) {
        return actorRepository.findById(id).orElseThrow();
    }

    public Actor update(Long id, Actor actor) {
        actor.setId(id);
        return actorRepository.save(actor);
    }

    public void deleteById(Long id) {
        actorRepository.deleteById(id);
    }

}
