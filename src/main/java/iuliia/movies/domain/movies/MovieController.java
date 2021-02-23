package iuliia.movies.domain.movies;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Api(tags="Movies")
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Add movie")
    public Movie create(@RequestBody @Valid Movie movie) {
        return movieService.create(movie);
    }

    @GetMapping
    @ApiOperation("Get all movies")
    public List<Movie> get() {
        return movieService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get movie by id")
    public Movie get(@PathVariable Long id) {
        return movieService.getById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update movie")
    public Movie update(@PathVariable Long id, @RequestBody @Valid Movie movie) {
        return movieService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete movie by id")
    public void delete(@PathVariable Long id) {
        movieService.deleteById(id);
    }
}
