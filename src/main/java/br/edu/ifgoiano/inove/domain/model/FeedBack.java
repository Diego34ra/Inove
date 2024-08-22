package br.edu.ifgoiano.inove.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tb_feedback")
public class FeedBack {
    @Id
    @Column(name = "feedback_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "usuario_id")
    private Usuario dicente;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private String comentario;

}
