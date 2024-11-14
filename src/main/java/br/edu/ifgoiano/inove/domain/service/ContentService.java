package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentInputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentSimpleOutputDTO;
import br.edu.ifgoiano.inove.domain.model.Content;

import java.util.List;

public interface ContentService {
    List<ContentSimpleOutputDTO> list(Long contentId);

    Content findById(Long sectionId, Long contentId);

    ContentOutputDTO findOneById(Long sectionId, Long contentId);

    ContentOutputDTO create (Long courseId, Long sectionId, ContentInputDTO newContent);

    ContentOutputDTO update (Long courseId, Long sectionId, Long contentId, ContentInputDTO newContent);

    void deleteById(Long sectionId, Long contentId);
}
