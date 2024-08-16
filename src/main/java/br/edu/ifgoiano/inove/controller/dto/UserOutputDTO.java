package br.edu.ifgoiano.inove.controller.dto;

import br.edu.ifgoiano.inove.domain.model.UsuarioRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOutputDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private UsuarioRole tipo;
}
