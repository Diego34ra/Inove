package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Curso;
import br.edu.ifgoiano.inove.domain.repository.CursoRepository;
import br.edu.ifgoiano.inove.domain.service.CursoService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public Curso create(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso findById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum curso com esse id."));
    }

    @Override
    public Curso update(Long id, Curso cursoUpdate) {
        Curso curso = findById(id);
        BeanUtils.copyProperties(cursoUpdate, curso, inoveUtils.getNullPropertyNames(cursoUpdate));
        return cursoRepository.save(curso);
    }

    @Override
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }
}
