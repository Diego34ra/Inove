package br.edu.ifgoiano.inove.controller.dto.request.courseDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseSimpleDTO {
    private Long id;

    private String name;
}
