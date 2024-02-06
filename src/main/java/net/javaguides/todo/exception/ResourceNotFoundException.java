package net.javaguides.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/*
 * @ResponseStatus(value = HttpStatus.NOT_FOUND)
 * By annotating the ResourceNotFoundException class with @ResponseStatus,
 * you are essentially specifying that when an instance of this exception is thrown
 * during the execution of your Spring application,
 * the framework should respond with an HTTP status code of 404.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}