package br.edu.ifgoiano.inove.controller.dto;

import br.edu.ifgoiano.inove.domain.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EscolaOutputDTO {

    private Long id;

    private String nome;

    private String cidade;

    private String estado;
}
