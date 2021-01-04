package iuliia.movies.domain.actors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iuliia.movies.domain.movies.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotEmpty
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    private LocalDate birthday;
    @JsonIgnore
    @ManyToMany(mappedBy = "cast")
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
