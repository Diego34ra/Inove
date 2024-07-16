package br.edu.ifgoiano.inove.controller.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class EntityInUseException extends DataIntegrityViolationException{
    public EntityInUseException(String msg) {
        super(msg);
    }
}
