package br.edu.ifgoiano.inove.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsuarioRole {

    DISCENTE,
    INSTRUTOR,
    ADMINISTRATOR;

    public static UsuarioRole getPadrao(){
        return DISCENTE;
    }
}
