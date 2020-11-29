package iuliia.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CastService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Transactional
    public void addCastMember(Long actorId, Long movieId) {
        Movie movie = movieRepository.getOne(movieId);
        Actor actor = actorRepository.findById(actorId).orElseThrow();
        movie.getCast().add(actor);
        actor.getMovies().add(movie);
    }
}
