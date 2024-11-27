package br.edu.ifgoiano.inove.controller.dto.request.courseDTOs;

import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.StudentSimpleOutputDTO;
import br.edu.ifgoiano.inove.domain.model.Course;
import br.edu.ifgoiano.inove.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackOutputDTO {
    private Long id;
    private StudentSimpleOutputDTO student;
    private String comment;
}
