package br.edu.ifgoiano.inove.controller.dto.request.schoolDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolOutputDTO {

    private Long id;
    private String name;
    private String email;
    private String city;
    private String federativeUnit;
}
