package iuliia.movies.domain.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import movies.iuliia.service.GetMovieRequest;
import movies.iuliia.service.GetMovieResponse;

@Endpoint
@RequiredArgsConstructor
public class MovieEndpoint {
    private final MovieService movieService;
    private final String NAMESPACE_URI = "http://iuliia.movies/service";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMovieRequest")
    @ResponsePayload
    public GetMovieResponse getMovie(@RequestPayload GetMovieRequest request) {
        var response = new GetMovieResponse();
        response.setMovie(movieService.getById(request.getId()));
        return response;
    }
}
