package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.domain.service.implementation.S3ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("api/inove/videos")
public class FileController {

    private final S3ServiceImpl s3Service;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public FileController(S3ServiceImpl s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Path tempFile = Files.createTempFile("temp-", file.getOriginalFilename());
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

            String keyName = file.getOriginalFilename();
            s3Service.uploadFile(bucketName, keyName, tempFile.toFile());

            Files.delete(tempFile);

            return ResponseEntity.ok("File uploaded successfully to S3.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the file temporarily.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file to S3.");
        }
    }
}
