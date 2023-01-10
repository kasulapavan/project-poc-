package net.thrymr.organisation.management.controller;
import net.thrymr.organisation.management.dto.MyCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class MyCustomExceptionController {
    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<String> handleEmptyInput(MyCustomException api){
        return new ResponseEntity<String>(api.getMessage(), HttpStatus.NOT_FOUND);
    }
}