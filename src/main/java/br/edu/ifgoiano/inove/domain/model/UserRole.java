package br.edu.ifgoiano.inove.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    STUDENT,
    INSTRUCTOR,
    ADMINISTRATOR;

    public static UserRole getDefault(){
        return STUDENT;
    }
}
