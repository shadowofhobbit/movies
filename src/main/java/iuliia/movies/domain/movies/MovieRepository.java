package iuliia.movies.domain.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("select m from Movie m join m.cast a on a.id = ?1")
    List<Movie> findMoviesByActorId(Long actorId);
}
