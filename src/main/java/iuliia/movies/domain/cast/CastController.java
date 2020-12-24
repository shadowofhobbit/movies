package iuliia.movies.domain.cast;

import iuliia.movies.domain.actors.Actor;
import iuliia.movies.domain.movies.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CastController {
    private final CastService castService;

    @PutMapping("/movies/{movieId}/cast/{actorId}")
    void addCastMember(@PathVariable Long actorId, @PathVariable Long movieId) {
        castService.addCastMember(actorId, movieId);
    }

    @GetMapping("/movies/{movieId}/cast")
    List<Actor> getCast(@PathVariable Long movieId) {
        return castService.getCast(movieId);
    }

    @GetMapping("/actors/{actorId}/movies")
    List<Movie> getMoviesForActor(@PathVariable Long actorId) {
        return castService.getMoviesForActor(actorId);
    }


}
