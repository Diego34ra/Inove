package br.edu.ifgoiano.inove.controller.dto.request.contentDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentOutputDTO {
    private Long id;
    private String description;
    private String title;
}
