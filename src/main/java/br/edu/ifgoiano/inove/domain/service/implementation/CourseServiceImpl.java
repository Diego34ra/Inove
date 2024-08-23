package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Course;
import br.edu.ifgoiano.inove.domain.repository.CoursoRepository;
import br.edu.ifgoiano.inove.domain.service.CourseService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CoursoRepository cursoRepository;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public Course create(Course course) {
        course.setCreationDate(LocalDateTime.now());
        return cursoRepository.save(course);
    }

    @Override
    public List<Course> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Course findById(Long courseId) {
        return cursoRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum curso com esse id."));
    }

    @Override
    public Course update(Long courseId, Course course) {
        Course savedCourse = findById(courseId);
        savedCourse.setLastUpdateDate(LocalDateTime.now());
        BeanUtils.copyProperties(course, savedCourse, inoveUtils.getNullPropertyNames(course));
        return cursoRepository.save(savedCourse);
    }

    @Override
    public void delete(Long courseId) {
        cursoRepository.deleteById(courseId);
    }

    @Override
    public Course saveUpdateDate(Long courseId) {
        Course course = findById(courseId);
        course.setLastUpdateDate(LocalDateTime.now());
        return cursoRepository.save(course);
    }
}
