package br.edu.ifgoiano.inove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/inove/videos")
public class VideoController {

    private final S3Client s3Client;

    private static final String BUCKET_NAME = "inove-bucket-streaming";

    @Autowired
    public VideoController(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(filename)
                .build();

        s3Client.putObject(putObjectRequest, Paths.get(file.getOriginalFilename()));
        return ResponseEntity.ok().body("Vídeo enviado com sucesso: " + filename);
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String fileName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(fileName)
                .build();
        ResponseInputStream<?> responseInputStream = s3Client.getObject(getObjectRequest);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(responseInputStream));
    }
}
