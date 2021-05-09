package com.energyinvestmentdata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rabiu Ademoh
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNameAlreadyExistsException extends RuntimeException {

    public ProjectNameAlreadyExistsException(String message) {
        super(message);
    }
}
