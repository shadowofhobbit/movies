package iuliia.movies.domain.cast;

import iuliia.movies.domain.actors.Actor;
import iuliia.movies.domain.actors.ActorRepository;
import iuliia.movies.domain.movies.Movie;
import iuliia.movies.domain.movies.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<Actor> getCast(Long movieId) {
        return actorRepository.findActorsByMovieId(movieId);
    }

    @Transactional(readOnly = true)
    public List<Movie> getMoviesForActor(Long actorId) {
        return movieRepository.findMoviesByActorId(actorId);
    }
}
