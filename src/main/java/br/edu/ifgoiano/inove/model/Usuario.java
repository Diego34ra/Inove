package br.edu.ifgoiano.inove.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity(name = "tb_usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    private Date dateNasc;

    @ManyToOne
    @JoinColumn(name = "escola_id")
    private Escola escola;

    @ManyToMany
    @JoinTable(name = "tb_discente_curso",
            joinColumns = @JoinColumn(name = "discente_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> discente_cursos;

    @ManyToMany
    @JoinTable(name = "tb_admin_curso",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> admin_cursos;

    @ManyToMany
    @JoinTable(name = "tb_instrutor_curso",
            joinColumns = @JoinColumn(name = "instrutor_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> instrutor_cursos;

    @Enumerated(EnumType.STRING)
    private UsuarioRole tipo;
}
