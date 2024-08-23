package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.sectionDTOs.SectionSimpleOutputDTO;
import br.edu.ifgoiano.inove.domain.model.Section;

import java.util.List;

public interface SectionService {
    List<SectionSimpleOutputDTO> list(Long courserId);

    SectionSimpleOutputDTO findOne(Long courseId, Long sectionId);

    Section create (Long courseId, Section newSection);

    Section update (Long courseId, Long sectionId, Section newSection);

    void deleteById(Long courseId, Long sectionId);
}
