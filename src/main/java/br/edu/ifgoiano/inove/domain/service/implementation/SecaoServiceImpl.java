package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.dto.SecaoSimpleOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceInUseException;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Curso;
import br.edu.ifgoiano.inove.domain.model.Secao;
import br.edu.ifgoiano.inove.domain.repository.SecaoRepository;
import br.edu.ifgoiano.inove.domain.service.CursoService;
import br.edu.ifgoiano.inove.domain.service.SecaoService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecaoServiceImpl implements SecaoService {
    @Autowired
    private SecaoRepository sectionRespository;
    @Autowired
    private CursoService courseService;
    @Autowired
    private MyModelMapper mapper;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public List<SecaoSimpleOutputDTO> list(Long courseId) {
        return mapper.toList(sectionRespository.findByCursoId(courseId), SecaoSimpleOutputDTO.class);
    }

    @Override
    public Secao findById(Long courseId, Long sectionId) {
        return sectionRespository.findByIdAndCursoId(courseId, sectionId)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhuma seção com esse id."));
    }

    @Override
    @Transactional
    public Secao create(Long courseId, Secao newSection) {
        newSection.setCurso(courseService.findById(courseId));
        return sectionRespository.save(newSection);
    }

    @Override
    @Transactional
    public Secao update(Long courseId, Long sectionId, Secao newSection) {
        Secao section = findById(courseId, sectionId);
        BeanUtils.copyProperties(newSection, section, inoveUtils.getNullPropertyNames(newSection));
        return sectionRespository.save(section);
    }

    @Override
    @Transactional
    public void deleteById(Long courseId, Long sectionId) {
        try{
            Secao section = findById(courseId, sectionId);
            sectionRespository.delete(section);
        } catch (DataIntegrityViolationException ex){
            throw new ResourceInUseException("O usuário de ID %d esta em uso e não pode ser removido.");
        }
    }
}
