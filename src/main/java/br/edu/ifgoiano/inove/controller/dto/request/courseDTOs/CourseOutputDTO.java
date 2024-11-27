package br.edu.ifgoiano.inove.controller.dto.request.courseDTOs;

import br.edu.ifgoiano.inove.controller.dto.request.sectionDTOs.SectionSimpleOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.StudentSimpleOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.UserSimpleOutputDTO;
import br.edu.ifgoiano.inove.domain.model.FeedBack;
import br.edu.ifgoiano.inove.domain.model.Section;
import br.edu.ifgoiano.inove.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseOutputDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private List<StudentSimpleOutputDTO> student;
    private List<UserSimpleOutputDTO> admins;
    private List<UserSimpleOutputDTO> instructors;
    private List<FeedBackOutputDTO> feedBacks;
    private List<SectionSimpleOutputDTO> sections;
}
