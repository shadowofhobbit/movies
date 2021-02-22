package iuliia.movies.domain.cast;

import iuliia.movies.domain.actors.Actor;
import iuliia.movies.domain.actors.ActorRepository;
import iuliia.movies.domain.movies.Movie;
import iuliia.movies.domain.movies.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CastService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Transactional
    public Mono<Void> addCastMember(String actorId, String movieId) {
        return actorRepository.findById(actorId)
                .flatMap(a -> movieRepository.findById(movieId))
                .flatMap(movie -> {
                    movie.getActorIds().add(actorId);
                    return movieRepository.save(movie);
                })
                .log()
                .then(
                        actorRepository.findById(actorId)
                                .flatMap(actor -> {
                                    actor.getMovieIds().add(movieId);
                                    return actorRepository.save(actor);
                                }))
                .log()
                .then();
    }

    @Transactional(readOnly = true)
    public Flux<Actor> getCast(String movieId) {
        return actorRepository.findActorsByMovieIdsContains(movieId);
    }

    @Transactional(readOnly = true)
    public Flux<Movie> getMoviesForActor(String actorId) {
        return movieRepository.findMoviesByActorIdsContaining(actorId);
    }
}
