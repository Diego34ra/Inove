package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentInputDTO;
import br.edu.ifgoiano.inove.domain.model.Content;
import br.edu.ifgoiano.inove.domain.model.ContentType;
import br.edu.ifgoiano.inove.domain.repository.ContentRepository;
import br.edu.ifgoiano.inove.domain.service.ContentService;
import br.edu.ifgoiano.inove.domain.service.implementation.S3ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("api/inove/videos")
public class FileController {

    private final S3ServiceImpl s3Service;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Autowired
    private MyModelMapper mapper;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentRepository contentRepository;

    public FileController(S3ServiceImpl s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @PathVariable Long courseId,
                                             @PathVariable Long sectionId,
                                             @RequestBody ContentInputDTO dto) {
        try {
            Path tempFile = Files.createTempFile("temp-", file.getOriginalFilename());
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

            String keyName = file.getOriginalFilename();
            s3Service.uploadFile(bucketName, keyName, tempFile.toFile());

            Files.delete(tempFile);

            Content newContent = mapper.mapTo(dto, Content.class);
            newContent.setFileUrl("https://" + bucketName + ".s3.amazonaws.com/" + keyName);
            newContent.setFileName(keyName);

//            contentRepository.create(courseId, sectionId, newContent);

            return ResponseEntity.ok("File uploaded successfully to S3.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the file temporarily.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file to S3.");
        }
    }

    @GetMapping("/stream/{fileName}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String fileName) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            InputStream videoStream = s3Service.getFileStream(getObjectRequest);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("video/mp4"));
            headers.set("Content-Disposition", "inline; filename=\"" + fileName + "\"");

            return new ResponseEntity<>(
                    new InputStreamResource(videoStream),
                    headers,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
