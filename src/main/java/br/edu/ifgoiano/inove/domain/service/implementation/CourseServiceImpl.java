package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Course;
import br.edu.ifgoiano.inove.domain.repository.CoursoRepository;
import br.edu.ifgoiano.inove.domain.service.CourseService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CoursoRepository cursoRepository;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public Course create(Course curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public List<Course> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum curso com esse id."));
    }

    @Override
    public Course update(Long id, Course cursoUpdate) {
        Course curso = findById(id);
        BeanUtils.copyProperties(cursoUpdate, curso, inoveUtils.getNullPropertyNames(cursoUpdate));
        return cursoRepository.save(curso);
    }

    @Override
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }
}
