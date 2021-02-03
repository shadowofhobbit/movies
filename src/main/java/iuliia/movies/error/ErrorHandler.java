package iuliia.movies.error;

import graphql.GraphQLError;
import graphql.servlet.GenericGraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ErrorHandler implements GraphQLErrorHandler {


    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        return list.stream()
                .map(error -> new GenericGraphQLError(error.getMessage()))
                .collect(Collectors.toList());
    }
}
