package iuliia.movies.domain.actors;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ActorRepository extends ReactiveMongoRepository<Actor, String> {

    Flux<Actor> findActorsByMovieIdsContains(String movieId);

}
