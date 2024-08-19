package br.edu.ifgoiano.inove.controller.dto.request.userDTOs;

import br.edu.ifgoiano.inove.controller.dto.request.schoolDTOs.EscolaOutputDTO;
import br.edu.ifgoiano.inove.domain.model.UsuarioRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscenteOutputDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private UsuarioRole tipo;

    private Date dateNasc;

    private EscolaOutputDTO escola;
}
