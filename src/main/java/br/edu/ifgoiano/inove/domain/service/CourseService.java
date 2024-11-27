package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.course.CourseRequestDTO;
import br.edu.ifgoiano.inove.controller.dto.response.course.CourseSimpleResponseDTO;
import br.edu.ifgoiano.inove.domain.model.Course;

import java.util.List;

public interface CourseService {

    br.edu.ifgoiano.inove.controller.dto.response.course.CourseResponseDTO create(CourseRequestDTO courseDTO);

    List<CourseSimpleResponseDTO> findAll();

    Course findById(Long courseId);

    br.edu.ifgoiano.inove.controller.dto.response.course.CourseResponseDTO findOneById(Long courseId);

    br.edu.ifgoiano.inove.controller.dto.response.course.CourseResponseDTO update(Long courseId, CourseRequestDTO courseDTO);

    void delete(Long courseId);

    Course saveUpdateDate(Long courseId);
}
