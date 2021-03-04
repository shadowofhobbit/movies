package iuliia.movies.domain.movies;

import org.springframework.stereotype.Service;

import movies.iuliia.service.movies.Movie;
import movies.iuliia.service.movies.MovieInvoice;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class MovieConverter {

    public Movie toMovie(MovieEntity entity) {
        var movie = new Movie();
        movie.setId(entity.getId());
        movie.setTitle(entity.getTitle());
        movie.setYear(entity.getYear());
        movie.setCountry(entity.getCountry());
        movie.setLength(entity.getLength());
        movie.setDirector(entity.getDirector());
        LocalDate premier = entity.getPremier();
        if (premier != null) {
            try {
                var xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                xmlGregorianCalendar.setYear(premier.getYear());
                xmlGregorianCalendar.setMonth(premier.getMonthValue());
                xmlGregorianCalendar.setDay(premier.getDayOfMonth());
                movie.setPremier(xmlGregorianCalendar);
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }
        }
        return movie;
    }

    MovieEntity toMovieEntity(Movie movie) {
        var entity = new MovieEntity();
        entity.setId(movie.getId());
        entity.setTitle(movie.getTitle());
        if (movie.getYear() != null) {
            entity.setYear(movie.getYear());
        }
        entity.setCountry(movie.getCountry());
        if (movie.getLength() != null) {
            entity.setLength(movie.getLength());
        }
        entity.setDirector(movie.getDirector());
        if (movie.getPremier() != null) {
            var date = LocalDateTime.parse(movie.getPremier().toString())
                    .atOffset(OffsetDateTime.now().getOffset()).toLocalDate();
            entity.setPremier(date);
        }
        return entity;
    }

    MovieEntity toMovieEntity(MovieInvoice movie) {
        var entity = new MovieEntity();
        entity.setTitle(movie.getTitle());
        if (movie.getYear() != null) {
            entity.setYear(movie.getYear());
        }
        entity.setCountry(movie.getCountry());
        if (movie.getLength() != null) {
            entity.setLength(movie.getLength());
        }
        entity.setDirector(movie.getDirector());
        if (movie.getPremier() != null) {
            var date = LocalDateTime.parse(movie.getPremier().toString())
                    .atOffset(OffsetDateTime.now().getOffset()).toLocalDate();
            entity.setPremier(date);
        }
        return entity;
    }
}
