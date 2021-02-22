package iuliia.movies.domain.movies;

import iuliia.movies.domain.cast.CastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final CastService castService;
    private final MovieRepository movieRepository;

    public Mono<Movie> create(Movie movie) {
        return movieRepository.save(movie);
    }

    public Flux<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Mono<Movie> getById(String id) {
        return movieRepository.findById(id);
    }

    @Transactional
    public Mono<Movie> update(String id, Movie movie) {
        return movieRepository.findById(id)
                .flatMap(found -> {
                    movie.setId(id);
                    movie.setActorIds(found.getActorIds());
                    return movieRepository.save(movie);
                });
    }

    public Mono<Void> deleteById(String id) {
        return castService.deleteMovieFromActors(id)
                .then(movieRepository.deleteById(id));
    }
}
