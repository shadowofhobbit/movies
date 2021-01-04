package iuliia.movies.domain.actors;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {
    @Query("select a from Actor a join fetch a.movies m where m.id = ?1 ")
    List<Actor> findActorsByMovieId(Long movieId);

    @Modifying
    @Query(value="delete from movie_cast where cast_id=?1", nativeQuery = true)
    void deleteActorFromMovies(long id);
}
