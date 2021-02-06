package iuliia.movies.domain.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select m from Movie m join fetch m.cast")
    List<Movie> findMoviesWithActors();

    @Query(value = "select m from Movie m join fetch m.cast where m.id = ?1")
    Optional<Movie> findMovieWithActorsById(long id);
}
