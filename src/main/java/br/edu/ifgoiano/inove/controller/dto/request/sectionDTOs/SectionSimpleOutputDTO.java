package br.edu.ifgoiano.inove.controller.dto.request.sectionDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SectionSimpleOutputDTO {
    private Long id;

    private String title;

    private String description;
}
