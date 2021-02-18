package iuliia.movies.domain.actors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Document
@Data
public class Actor {
    @Id
    private String id;
    @NotEmpty
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
    @JsonIgnore
    private Set<String> movieIds;

}
