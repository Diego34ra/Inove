package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentSimpleOutputDTO;
import br.edu.ifgoiano.inove.domain.model.Content;

import java.util.List;

public interface ContentService {
    List<ContentSimpleOutputDTO> list(Long contentId);

    Content findById(Long sectionId, Long contentId);

    Content create (Long courseId, Long sectionId, Content newContent);

    Content update (Long sectionId, Long contentId, Content newContent);

    void deleteById(Long sectionId, Long contentId);
}
