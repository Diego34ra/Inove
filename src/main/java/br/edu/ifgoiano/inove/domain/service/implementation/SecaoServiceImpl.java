package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.exceptions.ResourceInUseException;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Curso;
import br.edu.ifgoiano.inove.domain.model.Secao;
import br.edu.ifgoiano.inove.domain.repository.CursoRepository;
import br.edu.ifgoiano.inove.domain.repository.SecaoRepository;
import br.edu.ifgoiano.inove.domain.service.SecaoService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SecaoServiceImpl implements SecaoService {
    @Autowired
    private SecaoRepository sectionRespository;
    @Autowired
    private CursoRepository courseRespository;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public List<Secao> list() {
        return sectionRespository.findAll();
    }

    @Override
    public Secao findById(Long id) {
        return sectionRespository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhuma seção com esse id."));
    }

    @Override
    @Transactional
    public Secao create(Secao section) {
        return sectionRespository.save(section);
    }

    @Override
    @Transactional
    public Secao update(Long id, Secao sectionUpdate) {
        Secao section = findById(id);
        BeanUtils.copyProperties(sectionUpdate, section, inoveUtils.getNullPropertyNames(sectionUpdate));
        return sectionRespository.save(section);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try{
            Secao section = findById(id);
            sectionRespository.delete(section);
        } catch (DataIntegrityViolationException ex){
            throw new ResourceInUseException("O usuário de ID %d esta em uso e não pode ser removido.");
        }
    }

    //Provisorio
    private Curso defineCourse(Long id){
        return courseRespository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhuma curso com esse id."));
    }
}
