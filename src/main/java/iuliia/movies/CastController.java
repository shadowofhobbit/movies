package iuliia.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CastController {
    private final CastService castService;

    @PutMapping("/movie/{movieId}/cast/{actorId}")
    void addCastMember(@PathVariable Long actorId, @PathVariable Long movieId) {
        castService.addCastMember(actorId, movieId);
    }
}
