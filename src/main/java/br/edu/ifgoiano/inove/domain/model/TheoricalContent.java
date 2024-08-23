package br.edu.ifgoiano.inove.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TheoricalContent {
    @Column(name = "theorical_content_text")
    private String text;
    @Column(name = "theorical_content_file_name")
    private String fileName;
    @Column(name = "theorical_content_file_url")
    private String fileUrl;
}
