package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentSimpleInputDTO;
import br.edu.ifgoiano.inove.domain.model.Content;
import br.edu.ifgoiano.inove.domain.repository.ContentRepository;
import br.edu.ifgoiano.inove.domain.service.ContentService;
import br.edu.ifgoiano.inove.domain.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileServiceImpl implements FileService{
    private final S3ServiceImpl s3Service;

    public FileServiceImpl(S3ServiceImpl s3Service) {
        this.s3Service = s3Service;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Autowired
    private MyModelMapper mapper;

    @Autowired
    private ContentService contentService;

    @Override
    public String upload(MultipartFile file, Long courseId, Long sectionId, ContentSimpleInputDTO contentDTO) throws IOException {
        Path tempFile = Files.createTempFile("temp-", file.getOriginalFilename());
        Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        String keyName = file.getOriginalFilename();
        s3Service.uploadFile(bucketName, keyName, tempFile.toFile());

        Files.delete(tempFile);

        Content newContent = mapper.mapTo(contentDTO, Content.class);
        newContent.setFileUrl("https://" + bucketName + ".s3.amazonaws.com/" + keyName);
        newContent.setFileName(keyName);

        contentService.create(courseId, sectionId, newContent);

        return "File uploaded successfully to S3.";
    }

    @Override
    public InputStream stream(String fileName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        return s3Service.getFileStream(getObjectRequest);
    }
}
