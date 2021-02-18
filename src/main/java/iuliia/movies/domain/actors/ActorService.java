package iuliia.movies.domain.actors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;

    public Mono<Actor> create(Actor actor) {
        return actorRepository.save(actor);
    }

    public Flux<Actor> getAll() {
        return actorRepository.findAll();
    }

    public Mono<Actor> getById(String id) {
        return actorRepository.findById(id);
    }

    public Mono<Actor> update(String id, Actor actor) {
        actor.setId(id);
        return actorRepository.save(actor);
    }

    @Transactional
    public Mono<Void> deleteById(String id) {
        //actorRepository.deleteActorFromMovies(id);
        return actorRepository.deleteById(id);
    }

}
