package iuliia.movies.domain.actors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Actor> create(@RequestBody @Valid Actor actor) {
        return actorService.create(actor);
    }

    @GetMapping
    public Flux<Actor> get() {
        return actorService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Actor> get(@PathVariable String id) {
        return actorService.getById(id);
    }

    @PutMapping("/{id}")
    public Mono<Actor> update(@PathVariable String id, @RequestBody Actor actor) {
        return actorService.update(id, actor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return actorService.deleteById(id);
    }
}
