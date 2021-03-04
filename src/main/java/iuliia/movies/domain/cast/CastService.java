package iuliia.movies.domain.cast;

import iuliia.movies.domain.actors.ActorConverter;
import iuliia.movies.domain.actors.ActorEntity;
import iuliia.movies.domain.actors.ActorRepository;
import iuliia.movies.domain.movies.MovieConverter;
import iuliia.movies.domain.movies.MovieEntity;
import iuliia.movies.domain.movies.MovieRepository;
import lombok.RequiredArgsConstructor;
import movies.iuliia.service.actors.Actor;
import movies.iuliia.service.movies.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CastService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final MovieConverter movieConverter;
    private final ActorConverter actorConverter;

    @Transactional
    public void addCastMember(Long actorId, Long movieId) {
        MovieEntity movie = movieRepository.getOne(movieId);
        ActorEntity actor = actorRepository.findById(actorId).orElseThrow();
        movie.getCast().add(actor);
        actor.getMovies().add(movie);
    }

    @Transactional(readOnly = true)
    public List<Actor> getCast(Long movieId) {
        return actorRepository.findActorsByMovieId(movieId)
                .stream()
                .map(actorConverter::toActor)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Movie> getMoviesForActor(Long actorId) {
        return movieRepository.findMoviesByActorId(actorId)
                .stream()
                .map(movieConverter::toMovie)
                .collect(Collectors.toList());
    }
}
