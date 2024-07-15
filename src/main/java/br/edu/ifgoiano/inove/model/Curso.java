package br.edu.ifgoiano.inove.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "tb_curso")
public class Curso {
    @Id
    @Column(name = "curso_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @ManyToMany
    @JoinTable(name = "tb_discente_curso",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "discente_id"))
    private List<Usuario> discentes;

    @ManyToMany
    @JoinTable(name = "tb_admin_curso",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<Usuario> admins;

    @ManyToMany
    @JoinTable(name = "tb_instrutor_curso",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "instrutor_id"))
    private List<Usuario> instrutores ;

    @OneToMany(mappedBy = "curso")
    private List<FeedBack> feedBacks;

    @OneToMany(mappedBy = "curso")
    private List<Secao> secoes;
}
