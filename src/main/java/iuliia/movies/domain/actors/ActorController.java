package iuliia.movies.domain.actors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
@Api(tags = "Actors")
public class ActorController {
    private final ActorService actorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Add actor")
    public Actor create(@RequestBody @Valid Actor actor) {
        return actorService.create(actor);
    }

    @GetMapping
    @ApiOperation("Get all actors")
    public Iterable<Actor> get() {
        return actorService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get actor by id")
    public Actor get(@PathVariable Long id) {
        return actorService.getById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update actor")
    public Actor update(@PathVariable Long id, @RequestBody Actor actor) {
        return actorService.update(id, actor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete actor by id")
    public void delete(@PathVariable Long id) {
        actorService.deleteById(id);
    }
}
