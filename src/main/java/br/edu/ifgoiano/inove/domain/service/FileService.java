package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentSimpleInputDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String upload(Long courseId,
                  Long sectionId,
                  ContentSimpleInputDTO contentDTO) throws IOException;

    InputStream stream(String fileName);
}
