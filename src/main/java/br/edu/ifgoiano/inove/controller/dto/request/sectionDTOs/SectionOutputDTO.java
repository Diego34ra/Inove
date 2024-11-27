package br.edu.ifgoiano.inove.controller.dto.request.sectionDTOs;

import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentSimpleOutputDTO;
import br.edu.ifgoiano.inove.domain.model.Content;
import br.edu.ifgoiano.inove.domain.model.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionOutputDTO {
    private Long id;
    private String title;
    private String description;
    private List<ContentSimpleOutputDTO> contents;
}
