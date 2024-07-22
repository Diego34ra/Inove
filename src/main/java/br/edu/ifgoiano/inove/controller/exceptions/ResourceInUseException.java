package br.edu.ifgoiano.inove.controller.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class ResourceInUseException extends DataIntegrityViolationException{
    public ResourceInUseException(String msg) {
        super(msg);
    }
}
