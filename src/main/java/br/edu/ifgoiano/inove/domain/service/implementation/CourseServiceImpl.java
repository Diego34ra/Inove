package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.dto.request.courseDTOs.CourseInputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.courseDTOs.CourseOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.courseDTOs.CourseSimpleDTO;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Course;
import br.edu.ifgoiano.inove.domain.repository.CoursoRepository;
import br.edu.ifgoiano.inove.domain.service.CourseService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import lombok.val;
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
    private MyModelMapper mapper;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public CourseOutputDTO create(CourseInputDTO courseDTO) {
        Course course =  mapper.mapTo(courseDTO, Course.class);
        course.setCreationDate(LocalDateTime.now());
        return mapper.mapTo(cursoRepository.save(course),CourseOutputDTO.class);
    }

    @Override
    public List<CourseSimpleDTO> findAll() {
        return mapper.toList(cursoRepository.findAll(), CourseSimpleDTO.class);
    }

    @Override
    public CourseOutputDTO findOneById(Long courseId) {
        var course = cursoRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum curso com esse id."));
        return mapper.mapTo(course, CourseOutputDTO.class);
    }

    @Override
    public Course findById(Long courseId) {
        return cursoRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum curso com esse id."));
    }

    @Override
    public CourseOutputDTO update(Long courseId, CourseInputDTO courseDTO) {
        Course courseModel = mapper.mapTo(courseDTO, Course.class);
        Course savedCourse = findById(courseId);
        savedCourse.setLastUpdateDate(LocalDateTime.now());
        BeanUtils.copyProperties(courseModel, savedCourse, inoveUtils.getNullPropertyNames(courseModel));
        return mapper.mapTo(cursoRepository.save(savedCourse), CourseOutputDTO.class);
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
