package iuliia.movies.domain.movies;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieQuery implements GraphQLQueryResolver {
    private final MovieService movieService;

    public List<Movie> getMovies() {
        return movieService.getAll();
    }

    public Movie getMovie(long id) {
        return movieService.getById(id);
    }

}
