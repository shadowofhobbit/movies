package iuliia.movies.domain.cast;

import lombok.RequiredArgsConstructor;
import movies.iuliia.service.cast.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class CastEndpoint {
    private final String NAMESPACE_URI = "http://iuliia.movies/service/cast";
    private final CastService castService;

    @PayloadRoot(namespace = NAMESPACE_URI,  localPart = "addCastMemberRequest")
    @ResponsePayload
    AddCastMemberResponse addCastMember(@RequestPayload AddCastMemberRequest request) {
        castService.addCastMember(request.getActorId(), request.getMovieId());
        return new AddCastMemberResponse();
    }

    @PayloadRoot(namespace = NAMESPACE_URI,  localPart = "getCastRequest")
    @ResponsePayload
    GetCastResponse getCast(@RequestPayload GetCastRequest request) {
        var response = new GetCastResponse();
        response.getCast().addAll(castService.getCast(request.getMovieId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI,  localPart = "getMoviesForActorRequest")
    @ResponsePayload
    GetMoviesForActorResponse getMoviesForActor(@RequestPayload GetMoviesForActorRequest request) {
        var response = new GetMoviesForActorResponse();
        response.getMovie().addAll(castService.getMoviesForActor(request.getActorId()));
        return response;
    }


}
