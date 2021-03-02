package iuliia.movies.domain.actors;

import lombok.RequiredArgsConstructor;
import movies.iuliia.service.actors.Actor;
import movies.iuliia.service.actors.ActorInvoice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorConverter actorConverter;
    private final ActorRepository actorRepository;

    public Actor create(ActorInvoice actorInvoice) {
        var entity = actorConverter.toEntity(actorInvoice);
        var savedEntity = actorRepository.save(entity);
        return actorConverter.toActor(savedEntity);
    }

    public Collection<Actor> getAll() {
        return StreamSupport.stream(actorRepository.findAll().spliterator(), false)
                .map(actorConverter::toActor)
                .collect(Collectors.toList());
    }

    public Actor getById(Long id) {
        var actorEntity = actorRepository.findById(id).orElseThrow();
        return actorConverter.toActor(actorEntity);
    }

    public Actor update(Actor actor) {
        var entity = actorConverter.toEntity(actor);
        var savedEntity = actorRepository.save(entity);
        return actorConverter.toActor(savedEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        actorRepository.deleteActorFromMovies(id);
        actorRepository.deleteById(id);
    }

}
