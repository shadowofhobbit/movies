package iuliia.movies.domain.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@RequestBody @Valid Movie movie) {
        return movieService.create(movie);
    }

    @GetMapping
    public List<Movie> get() {
        return movieService.getAll();
    }

    @GetMapping("/{id}")
    public Movie get(@PathVariable Long id) {
        return movieService.getById(id);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody @Valid Movie movie) {
        return movieService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        movieService.deleteById(id);
    }
}
