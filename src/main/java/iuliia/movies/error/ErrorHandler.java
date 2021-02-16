package iuliia.movies.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<?> handleNoSuchElement() {
        return ResponseEntity.notFound().build();
    }
}
