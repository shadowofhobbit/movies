package iuliia.movies.domain.actors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iuliia.movies.domain.movies.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
public class Actor {
    private Long id;
    @NotEmpty
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
    @JsonIgnore
    private Set<Movie> movies;

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Actor actor = (Actor) other;
        return id != null && id.equals(actor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
