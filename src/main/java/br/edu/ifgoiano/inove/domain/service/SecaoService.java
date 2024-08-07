package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.SecaoSimpleOutputDTO;
import br.edu.ifgoiano.inove.domain.model.Secao;

import java.util.List;

public interface SecaoService {
    List<SecaoSimpleOutputDTO> list(Long courserId);

    SecaoSimpleOutputDTO getOne(Long courseId, Long sectionId);

    Secao create (Long courseId, Secao newSection);

    Secao update (Long courseId, Long sectionId, Secao newSection);

    void deleteById(Long courseId, Long sectionId);
}
