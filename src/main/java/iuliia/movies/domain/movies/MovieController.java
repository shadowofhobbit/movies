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
    public MovieEntity create(@RequestBody @Valid MovieEntity movie) {
        return movieService.create(movie);
    }

    @GetMapping
    public List<MovieEntity> get() {
        return movieService.getAll();
    }

    @PutMapping("/{id}")
    public MovieEntity update(@PathVariable Long id, @RequestBody @Valid MovieEntity movie) {
        return movieService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        movieService.deleteById(id);
    }
}
