package iuliia.movies.domain.movies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iuliia.movies.domain.actors.Actor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class Movie {
    private Long id;
    @NotEmpty
    private String title;
    private int year;
    private String country;
    @JsonIgnore
    private Set<Actor> cast;
    private LocalDate premier;
    private int length;
    private String director;

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Movie movie = (Movie) other;
        return id != null && id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
