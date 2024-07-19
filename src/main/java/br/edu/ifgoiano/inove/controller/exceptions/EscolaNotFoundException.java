package br.edu.ifgoiano.inove.controller.exceptions;

public class EscolaNotFoundException extends EntityNotFoundException {
    public EscolaNotFoundException(String message) {
        super(message);
    }

    public EscolaNotFoundException(Long escolaId){
        this(String.format("A Escola com o código %d foi encontrada", escolaId));
    }
}
