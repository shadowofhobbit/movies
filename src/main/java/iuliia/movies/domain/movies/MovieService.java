package iuliia.movies.domain.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Movie getById(Long id) {
        return movieRepository.findById(id).orElseThrow();
    }

    public Movie update(Long id, Movie movie) {
        movie.setId(id);
        return movieRepository.save(movie);
    }

    public void deleteById(Long id) {
        movieRepository.deleteMovieCast(id);
        movieRepository.deleteById(id);
    }
}
