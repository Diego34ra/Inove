package br.edu.ifgoiano.inove.controller.exceptions;

public abstract class EntityNotFoundException extends RuntimeException {
    private static  final  long serialVersionUID = 1L;

    public EntityNotFoundException(String message){
        super(message);
    }
}
