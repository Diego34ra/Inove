package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.dto.ConteudoSimpleOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceInUseException;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Conteudo;
import br.edu.ifgoiano.inove.domain.repository.ConteudoRepository;
import br.edu.ifgoiano.inove.domain.service.ConteudoService;
import br.edu.ifgoiano.inove.domain.service.SecaoService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConteudoServiceImpl implements ConteudoService {
    @Autowired
    private ConteudoRepository contentRespository;
    @Autowired
    private SecaoServiceImpl sectionService;
    @Autowired
    private MyModelMapper mapper;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public List<ConteudoSimpleOutputDTO> list(Long sectionId) {
        return mapper.toList(contentRespository.findBySecaoId(sectionId), ConteudoSimpleOutputDTO.class);
    }

    @Override
    public Conteudo findById(Long sectionId, Long contentId) {
        return contentRespository.findByIdAndSecaoId(contentId, sectionId)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhum conteudo com esse id."));
    }

    @Override
    @Transactional
    public Conteudo create(Long courseId, Long sectionId, Conteudo newContent) {
        newContent.setSecao(sectionService.findByIdAndCursoId(courseId, sectionId));
        return contentRespository.save(newContent);
    }

    @Override
    @Transactional
    public Conteudo update(Long sectionId, Long contentId, Conteudo newContent) {
        Conteudo savedContent = findById(sectionId, contentId);
        BeanUtils.copyProperties(newContent, savedContent, inoveUtils.getNullPropertyNames(newContent));
        return contentRespository.save(savedContent);
    }

    @Override
    @Transactional
    public void deleteById(Long courseId, Long sectionId) {
        try{
            Conteudo section = findById(courseId, sectionId);
            contentRespository.delete(section);
        } catch (DataIntegrityViolationException ex){
            throw new ResourceInUseException("O usuário de ID %d esta em uso e não pode ser removido.");
        }
    }
}
