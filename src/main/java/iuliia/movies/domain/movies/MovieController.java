package iuliia.movies.domain.movies;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Api(tags="Movies")
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Add movie")
    public Mono<Movie> create(@RequestBody @Valid Movie movie) {
        return movieService.create(movie);
    }

    @GetMapping
    @ApiOperation("Get all movies")
    public Flux<Movie> get() {
        return movieService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get movie by id")
    public Mono<Movie> get(@PathVariable String id) {
        return movieService.getById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update movie")
    public Mono<Movie> update(@PathVariable String id, @RequestBody @Valid Movie movie) {
        return movieService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete movie by id")
    public Mono<Void> delete(@PathVariable String id) {
        return movieService.deleteById(id);
    }
}
