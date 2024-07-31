package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.domain.model.Conteudo;
import br.edu.ifgoiano.inove.domain.model.Curso;

import java.util.List;

public interface ConteudoService {

    Conteudo create(Conteudo conteudo);

    List<Conteudo> findAll();

    Conteudo findById(Long id);

    Conteudo update(Long id, Conteudo conteudo);

    void delete(Long id);
}
