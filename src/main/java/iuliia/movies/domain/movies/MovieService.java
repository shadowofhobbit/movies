package iuliia.movies.domain.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieService {
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
        movie.setId(id);
        return movieRepository.save(movie);
    }

    public Mono<Void> deleteById(String id) {
        return movieRepository.deleteById(id);
    }
}
