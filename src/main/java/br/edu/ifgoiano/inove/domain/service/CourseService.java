package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.courseDTOs.CourseInputDTO;
import br.edu.ifgoiano.inove.domain.model.Course;

import java.util.List;

public interface CourseService {

    Course create(CourseInputDTO courseDTO);

    List<Course> findAll();

    Course findById(Long courseId);

    Course update(Long courseId, CourseInputDTO courseDTO);

    void delete(Long courseId);

    Course saveUpdateDate(Long courseId);
}
