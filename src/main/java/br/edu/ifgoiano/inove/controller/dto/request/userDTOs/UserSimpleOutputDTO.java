package br.edu.ifgoiano.inove.controller.dto.request.userDTOs;

import br.edu.ifgoiano.inove.domain.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleOutputDTO {

    private Long id;

    private String name;

    private UserRole role;
}
