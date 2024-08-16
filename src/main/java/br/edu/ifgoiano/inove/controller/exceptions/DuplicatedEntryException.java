package br.edu.ifgoiano.inove.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.sql.SQLIntegrityConstraintViolationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicatedEntryException extends SQLIntegrityConstraintViolationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicatedEntryException(String message) {
        super(message);
    }
}
