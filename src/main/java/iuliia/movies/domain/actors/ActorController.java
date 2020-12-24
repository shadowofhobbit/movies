package iuliia.movies.domain.actors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor create(@RequestBody @Valid Actor actor) {
        return actorService.create(actor);
    }

    @GetMapping
    public Iterable<Actor> get() {
        return actorService.getAll();
    }

    @GetMapping("/{id}")
    public Actor get(@PathVariable Long id) {
        return actorService.getById(id);
    }

    @PutMapping("/{id}")
    public Actor update(@PathVariable Long id, @RequestBody Actor actor) {
        return actorService.update(id, actor);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id) {
        actorService.deleteById(id);
    }
}
