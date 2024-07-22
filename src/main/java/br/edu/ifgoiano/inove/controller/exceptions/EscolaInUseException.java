package br.edu.ifgoiano.inove.controller.exceptions;

public class EscolaInUseException extends ResourceInUseException {
    public EscolaInUseException(String msg) {
        super(msg);
    }

    public EscolaInUseException(Long escolaId){
        this(String.format("A Escola com o código %d Esta em uso portanto não pode ser excluida", escolaId));
    }
}
