package iuliia.movies;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String title;
    private int year;
    private String country;
    @ManyToMany
    @JoinTable(name="movie_cast",
            joinColumns= @JoinColumn(name="movie_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="cast_id", referencedColumnName="id"))
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
