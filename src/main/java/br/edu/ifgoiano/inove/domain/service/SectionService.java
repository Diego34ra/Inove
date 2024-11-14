package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.sectionDTOs.SectionInputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.sectionDTOs.SectionOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.sectionDTOs.SectionSimpleOutputDTO;
import br.edu.ifgoiano.inove.domain.model.Section;

import java.util.List;

public interface SectionService {
    List<SectionSimpleOutputDTO> list(Long courserId);

    SectionOutputDTO findOne(Long courseId, Long sectionId);

    SectionOutputDTO create (Long courseId, SectionInputDTO newSectionDTO);

    SectionOutputDTO update (Long courseId, Long sectionId, SectionInputDTO newSectionDTO);

    void deleteById(Long courseId, Long sectionId);
}
