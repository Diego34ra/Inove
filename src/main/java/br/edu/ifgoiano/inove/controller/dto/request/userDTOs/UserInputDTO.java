package br.edu.ifgoiano.inove.controller.dto.request.userDTOs;

import br.edu.ifgoiano.inove.domain.model.School;
import br.edu.ifgoiano.inove.domain.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInputDTO {
    private String name;
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos")
    private String cpf;
    @Email(message = "O email é inválido")
    private String email;
    private String password;
    private UserRole userRole;
}
