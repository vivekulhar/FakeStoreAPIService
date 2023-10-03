package dev.vivek.productservicetutorial.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND, reason = "No product with given id found")
public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }
}
