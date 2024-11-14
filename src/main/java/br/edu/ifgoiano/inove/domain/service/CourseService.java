package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.courseDTOs.CourseInputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.courseDTOs.CourseOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.courseDTOs.CourseSimpleDTO;
import br.edu.ifgoiano.inove.domain.model.Course;

import java.util.List;

public interface CourseService {

    CourseOutputDTO create(CourseInputDTO courseDTO);

    List<CourseSimpleDTO> findAll();

    Course findById(Long courseId);

    CourseOutputDTO findOneById(Long courseId);

    CourseOutputDTO update(Long courseId, CourseInputDTO courseDTO);

    void delete(Long courseId);

    Course saveUpdateDate(Long courseId);
}
