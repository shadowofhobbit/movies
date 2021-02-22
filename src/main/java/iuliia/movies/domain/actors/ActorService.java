package iuliia.movies.domain.actors;

import iuliia.movies.domain.cast.CastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final CastService castService;
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
        return actorRepository.findById(id)
                .flatMap(found -> {
                    actor.setId(id);
                    actor.setMovieIds(found.getMovieIds());
                    return actorRepository.save(actor);
                });
    }

    @Transactional
    public Mono<Void> deleteById(String id) {
        return castService.deleteActorFromMovies(id)
                .then(actorRepository.deleteById(id));
    }

}
