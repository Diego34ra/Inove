package br.edu.ifgoiano.inove.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class VideoContent {
    @Column(name = "video_title")
    private String title;
    @Column(name = "video_url")
    private String videoUrl;
}
