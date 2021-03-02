package iuliia.movies.domain.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import iuliia.movies.domain.actors.ActorEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties("hibernateLazyInitializer")
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotEmpty
    private String title;
    private int year;
    private String country;
    @ManyToMany
    @JoinTable(name="movie_cast",
            joinColumns= @JoinColumn(name="movie_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="cast_id", referencedColumnName="id"))
    private Set<ActorEntity> cast;
    private LocalDate premier;
    private int length;
    private String director;

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        MovieEntity movie = (MovieEntity) other;
        return id != null && id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
