package br.edu.ifgoiano.inove.controller.dto.request.contentDTOs;

import br.edu.ifgoiano.inove.domain.model.Section;
import br.edu.ifgoiano.inove.domain.model.TheoricalContent;
import br.edu.ifgoiano.inove.domain.model.VideoContent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentInputDTO {

    private String description;
    private String title;
    private VideoContent video;
    private TheoricalContent theoricalContent;
    private Section section;
}
