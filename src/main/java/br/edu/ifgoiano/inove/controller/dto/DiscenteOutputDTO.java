package br.edu.ifgoiano.inove.controller.dto;

import br.edu.ifgoiano.inove.domain.model.Escola;
import br.edu.ifgoiano.inove.domain.model.UsuarioRole;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
