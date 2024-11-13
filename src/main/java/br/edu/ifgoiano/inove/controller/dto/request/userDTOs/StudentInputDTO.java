package br.edu.ifgoiano.inove.controller.dto.request.userDTOs;

import br.edu.ifgoiano.inove.domain.model.Course;
import br.edu.ifgoiano.inove.domain.model.School;
import br.edu.ifgoiano.inove.domain.model.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDTO {

    private String name;
    private String cpf;
    private String email;
    private String password;
    private Date birthDate;
    private School school;
    private List<Course> student_courses;
    private UserRole role = UserRole.STUDENT;
}
