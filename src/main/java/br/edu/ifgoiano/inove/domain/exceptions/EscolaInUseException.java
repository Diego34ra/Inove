package br.edu.ifgoiano.inove.domain.exceptions;

public class EscolaInUseException extends EntityInUseException{
    public EscolaInUseException(String msg) {
        super(msg);
    }

    public EscolaInUseException(Long escolaId){
        this(String.format("A Escola com o código %d Esta em uso portanto não pode ser excluida", escolaId));
    }
}
