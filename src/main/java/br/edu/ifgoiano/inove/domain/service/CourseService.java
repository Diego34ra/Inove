package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.domain.model.Course;

import java.util.List;

public interface CourseService {

    Course create(Course curso);

    List<Course> findAll();

    Course findById(Long id);

    Course update(Long id, Course curso);

    void delete(Long id);
}
