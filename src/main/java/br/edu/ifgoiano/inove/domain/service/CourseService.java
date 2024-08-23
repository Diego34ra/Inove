package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.domain.model.Course;

import java.util.List;

public interface CourseService {

    Course create(Course course);

    List<Course> findAll();

    Course findById(Long courseId);

    Course update(Long courseId, Course course);

    void delete(Long courseId);

    Course saveUpdateDate(Long courseId);
}
