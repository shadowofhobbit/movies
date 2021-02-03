package iuliia.movies.domain.actors;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActorQuery implements GraphQLQueryResolver {

    private final ActorService actorService;

    public Iterable<Actor> getActors() {
        return actorService.getAll();
    }


    public Actor getActor(long id) {
        return actorService.getById(id);
    }


}
