package br.edu.ifgoiano.inove.controller.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDetails {

    private Date timestamp;
    private int status;
    private String message;
    private String path;

    public ErrorDetails(String message) {
        this.message = message;
    }

    public ErrorDetails(Date timestamp, int status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
