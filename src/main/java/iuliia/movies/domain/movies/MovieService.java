package iuliia.movies.domain.movies;

import iuliia.movies.domain.cast.CastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;
    private final CastService castService;

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    public List<Movie> getAll() {
        return movieRepository.findMoviesWithActors();
    }

    @Transactional(readOnly = true)
    public Movie getById(Long id) {
        return movieRepository.findMovieWithActorsById(id).orElseThrow();
    }

    public Movie update(Long id, Movie movie) {
        movie.setId(id);
        movie.setCast(movieRepository.getOne(id).getCast());
        return movieRepository.save(movie);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    public Movie addActor(long movieId, long actorId) {
        castService.addCastMember(actorId, movieId);
        return getById(movieId);
    }
}
