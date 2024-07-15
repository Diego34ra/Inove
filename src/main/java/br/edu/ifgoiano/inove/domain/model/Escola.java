package br.edu.ifgoiano.inove.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity (name = "tb_escola")
@Getter
@Setter
public class Escola {

    @Id
    @Column(name = "escola_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cidade;

    private String estado;

    @OneToMany(mappedBy = "escola")
    private List<Usuario> dicentes;
}
