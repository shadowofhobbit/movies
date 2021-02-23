package iuliia.movies.domain.cast;

import io.swagger.annotations.ApiOperation;
import iuliia.movies.domain.actors.Actor;
import iuliia.movies.domain.movies.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CastController {
    private final CastService castService;

    @PutMapping("/movies/{movieId}/cast/{actorId}")
    @ApiOperation(tags = "Movies", value = "Add actor to movie cast")
    Mono<Void> addCastMember(@PathVariable String actorId, @PathVariable String movieId) {
        return castService.addCastMember(actorId, movieId);
    }

    @GetMapping("/movies/{movieId}/cast")
    @ApiOperation(tags = "Movies", value = "Get movie cast")
    Flux<Actor> getCast(@PathVariable String movieId) {
        return castService.getCast(movieId);
    }

    @GetMapping("/actors/{actorId}/movies")
    @ApiOperation(tags = "Actors", value = "Get movies with actor")
    Flux<Movie> getMoviesForActor(@PathVariable String actorId) {
        return castService.getMoviesForActor(actorId);
    }


}
