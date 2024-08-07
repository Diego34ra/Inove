package br.edu.ifgoiano.inove.controller.dto;

import br.edu.ifgoiano.inove.domain.model.Curso;
import br.edu.ifgoiano.inove.domain.model.Escola;
import br.edu.ifgoiano.inove.domain.model.UsuarioRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminOutputDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private UsuarioRole tipo;
}
