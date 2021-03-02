package iuliia.movies.domain.actors;

import lombok.RequiredArgsConstructor;
import movies.iuliia.service.actors.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ActorEndpoint {
    private final ActorService actorService;
    private final String NAMESPACE_URI = "http://iuliia.movies/service/actors";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createActorRequest")
    @ResponsePayload
    public CreateActorResponse create(@RequestPayload CreateActorRequest request) {
        var response = new CreateActorResponse();
        response.setActor(actorService.create(request.getActor()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getActorRequest")
    @ResponsePayload
    public GetActorResponse getActor(@RequestPayload GetActorRequest request) {
        var response = new GetActorResponse();
        response.setActor(actorService.getById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllActorsRequest")
    @ResponsePayload
    public GetAllActorsResponse getAll(@RequestPayload GetAllActorsRequest request) {
        var response = new GetAllActorsResponse();
        response.getActor().addAll(actorService.getAll());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateActorRequest")
    @ResponsePayload
    public UpdateActorResponse update(@RequestPayload UpdateActorRequest request) {
        var response = new UpdateActorResponse();
        response.setActor(actorService.update(request.getActor()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteActorRequest")
    @ResponsePayload
    public DeleteActorResponse deleteActor(@RequestPayload DeleteActorRequest request) {
        actorService.deleteById(request.getId());
        return new DeleteActorResponse();
    }
}
