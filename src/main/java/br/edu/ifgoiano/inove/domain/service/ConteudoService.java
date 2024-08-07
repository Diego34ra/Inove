package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.ConteudoSimpleOutputDTO;
import br.edu.ifgoiano.inove.domain.model.Conteudo;

import java.util.List;

public interface ConteudoService {
    List<ConteudoSimpleOutputDTO> list(Long contentId);

    Conteudo findById(Long sectionId, Long contentId);

    Conteudo create (Long courseId, Long sectionId, Conteudo newContent);

    Conteudo update (Long sectionId, Long contentId, Conteudo newContent);

    void deleteById(Long sectionId, Long contentId);
}
