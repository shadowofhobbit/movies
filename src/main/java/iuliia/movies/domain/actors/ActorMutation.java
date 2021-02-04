package iuliia.movies.domain.actors;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Component
@RequiredArgsConstructor
public class ActorMutation implements GraphQLMutationResolver {
    private final ActorService actorService;

    public Actor createActor(@Valid Actor actor) {
        return actorService.create(actor);
    }

    public Actor updateActor(long id, @Valid Actor actor) {
        return actorService.update(id, actor);
    }

    public long deleteActor(long id) {
        actorService.deleteById(id);
        return id;
    }
}
