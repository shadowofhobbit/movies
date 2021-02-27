package iuliia.movies.domain.movies;

import org.springframework.stereotype.Service;

import movies.iuliia.service.Movie;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;

@Service
public class MovieConverter {

    Movie convert(MovieEntity entity) {
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
}
