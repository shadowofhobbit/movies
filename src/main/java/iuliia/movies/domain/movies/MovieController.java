package iuliia.movies.domain.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Movie> create(@RequestBody @Valid Movie movie) {
        return movieService.create(movie);
    }

    @GetMapping
    public Flux<Movie> get() {
        return movieService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Movie> get(@PathVariable String id) {
        return movieService.getById(id);
    }

    @PutMapping("/{id}")
    public Mono<Movie> update(@PathVariable String id, @RequestBody @Valid Movie movie) {
        return movieService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return movieService.deleteById(id);
    }
}
