package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.domain.model.Curso;

import java.util.List;

public interface CursoService {

    Curso create(Curso curso);

    List<Curso> findAll();

    Curso findById(Long id);

    Curso update(Long id, Curso curso);

    void delete(Long id);
}
