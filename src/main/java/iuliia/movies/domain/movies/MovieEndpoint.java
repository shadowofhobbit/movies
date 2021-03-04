package iuliia.movies.domain.movies;

import lombok.RequiredArgsConstructor;
import movies.iuliia.service.movies.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
@RequiredArgsConstructor
public class MovieEndpoint {
    private final MovieService movieService;
    private final String NAMESPACE_URI = "http://iuliia.movies/service/movies";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createMovieRequest")
    @ResponsePayload
    public CreateMovieResponse create(@RequestPayload CreateMovieRequest request) {
        var response = new CreateMovieResponse();
        response.setMovie(movieService.create(request.getMovie()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMovieRequest")
    @ResponsePayload
    public GetMovieResponse getMovie(@RequestPayload GetMovieRequest request) {
        var response = new GetMovieResponse();
        response.setMovie(movieService.getById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllMoviesRequest")
    @ResponsePayload
    public GetAllMoviesResponse getAll(@RequestPayload GetAllMoviesRequest request) {
        var response = new GetAllMoviesResponse();
        response.getMovie().addAll(movieService.getAll());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateMovieRequest")
    @ResponsePayload
    public UpdateMovieResponse create(@RequestPayload UpdateMovieRequest request) {
        var response = new UpdateMovieResponse();
        response.setMovie(movieService.update(request.getMovie()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMovieRequest")
    @ResponsePayload
    public DeleteMovieResponse delete(@RequestPayload DeleteMovieRequest request) {
        var response = new DeleteMovieResponse();
        movieService.deleteById(request.getId());
        return response;
    }
}
