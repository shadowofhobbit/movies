package iuliia.movies.domain.movies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Document
@Data
public class Movie {
    @Id
    private String id;
    @NotEmpty
    private String title;
    private Integer year;
    private String country;
    @JsonIgnore
    private Set<String> actorIds;
    private LocalDate premier;
    private Integer length;
    private String director;

}
