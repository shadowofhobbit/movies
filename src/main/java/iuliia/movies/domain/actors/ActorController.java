package iuliia.movies.domain.actors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<Actor> create(@RequestBody @Valid Actor actor) {
        return actorService.create(actor);
    }

    @GetMapping
    @ApiOperation("Get all actors")
    public Flux<Actor> get() {
        return actorService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get actor by id")
    public Mono<Actor> get(@PathVariable String id) {
        return actorService.getById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update actor")
    public Mono<Actor> update(@PathVariable String id, @RequestBody Actor actor) {
        return actorService.update(id, actor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete actor by id")
    public Mono<Void> delete(@PathVariable String id) {
        return actorService.deleteById(id);
    }
}
