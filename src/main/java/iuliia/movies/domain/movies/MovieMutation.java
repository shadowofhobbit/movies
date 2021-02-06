package iuliia.movies.domain.movies;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Component
@RequiredArgsConstructor
public class MovieMutation implements GraphQLMutationResolver {
    private final MovieService movieService;

    public Movie createMovie(@Valid Movie movie) {
        return movieService.create(movie);
    }

    public Movie updateMovie(long id, @Valid Movie movie) {
        return movieService.update(id, movie);
    }

    public long deleteMovie(long id) {
        movieService.deleteById(id);
        return id;
    }

    public Movie addMovieActor(long movieId, long actorId) {
        return movieService.addActor(movieId, actorId);
    }
}
