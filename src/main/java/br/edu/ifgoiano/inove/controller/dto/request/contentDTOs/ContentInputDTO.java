package br.edu.ifgoiano.inove.controller.dto.request.contentDTOs;

import br.edu.ifgoiano.inove.domain.model.ContentType;
import br.edu.ifgoiano.inove.domain.model.Section;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentInputDTO {

    private String description;
    private String title;
    private ContentType contentType;
    private String fileUrl;
    private String fileName;
}
