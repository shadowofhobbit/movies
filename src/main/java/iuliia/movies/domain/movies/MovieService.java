package iuliia.movies.domain.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import movies.iuliia.service.Movie;
import movies.iuliia.service.MovieInvoice;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieConverter movieConverter;
    private final MovieRepository movieRepository;

    public Movie create(MovieInvoice movie) {
        var entity = movieConverter.toMovieEntity(movie);
        var savedEntity = movieRepository.save(entity);
        return movieConverter.toMovie(savedEntity);
    }

    public List<Movie> getAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieConverter::toMovie)
                .collect(Collectors.toList());
    }

    public Movie getById(Long id) {
        var movieEntity = movieRepository.findById(id).orElseThrow();
        return movieConverter.toMovie(movieEntity);
    }

    @Transactional
    public Movie update(Movie movie) {
        var oldMovie = movieRepository.getOne(movie.getId());
        var movieEntity = movieConverter.toMovieEntity(movie);
        movieEntity.setCast(oldMovie.getCast());
        var savedEntity = movieRepository.save(movieEntity);
        return movieConverter.toMovie(savedEntity);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
