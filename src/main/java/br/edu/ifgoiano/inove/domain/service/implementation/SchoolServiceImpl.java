package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.dto.request.schoolDTOs.SchoolOutputDTO;
import br.edu.ifgoiano.inove.controller.exceptions.*;
import br.edu.ifgoiano.inove.domain.model.School;
import br.edu.ifgoiano.inove.domain.repository.SchoolRespository;
import br.edu.ifgoiano.inove.domain.service.SchoolService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRespository escolaRespository;

    @Autowired
    private MyModelMapper mapper;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public List<SchoolOutputDTO> list() {
        return mapper.toList(escolaRespository.findAll(), SchoolOutputDTO.class);
    }


    @Override
    public SchoolOutputDTO findOneById(Long id) {
        School school = escolaRespository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhuma escola com esse id."));
        return mapper.mapTo(school, SchoolOutputDTO.class);
    }

    @Override
    @Transactional
    public School create(School escola) {
        return escolaRespository.save(escola);
    }

    @Override
    @Transactional
    public School update(Long id, School escolaUpdate) {
        School escola = findById(id);
        BeanUtils.copyProperties(escolaUpdate, escola, inoveUtils.getNullPropertyNames(escolaUpdate));
        return escolaRespository.save(escola);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try{
            School escola = findById(id);
            escolaRespository.delete(escola);
        } catch (DataIntegrityViolationException ex){
            throw new ResourceInUseException("O usuário de ID %d esta em uso e não pode ser removido.");
        }
    }

    @Override
    public School findById(Long id) {
        return escolaRespository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhuma escola com esse id."));
    }
}
