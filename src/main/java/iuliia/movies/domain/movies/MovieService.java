package iuliia.movies.domain.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import movies.iuliia.service.Movie;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieConverter movieConverter;
    private final MovieRepository movieRepository;

    public MovieEntity create(MovieEntity movie) {
        return movieRepository.save(movie);
    }

    public List<MovieEntity> getAll() {
        return movieRepository.findAll();
    }

    public Movie getById(Long id) {
        var movieEntity = movieRepository.findById(id).orElseThrow();
        return movieConverter.convert(movieEntity);
    }

    @Transactional
    public MovieEntity update(Long id, MovieEntity movie) {
        movie.setId(id);
        var oldMovie = movieRepository.getOne(id);
        movie.setCast(oldMovie.getCast());
        return movieRepository.save(movie);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
