package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.exceptions.*;
import br.edu.ifgoiano.inove.domain.model.Escola;
import br.edu.ifgoiano.inove.domain.repository.EscolaRespository;
import br.edu.ifgoiano.inove.domain.service.EscolaService;
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
public class EscolaServiceImpl implements EscolaService {
    @Autowired
    private EscolaRespository escolaRespository;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public List<Escola> list() {
        return escolaRespository.findAll();
    }

    @Override
    public Escola findById(Long id) {
        return escolaRespository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhuma escola com esse id."));
    }

    @Override
    @Transactional
    public Escola create(Escola escola) {
        return escolaRespository.save(escola);
    }

    @Override
    @Transactional
    public Escola update(Long id, Escola escolaUpdate) {
        Escola escola = findById(id);
        BeanUtils.copyProperties(escolaUpdate, escola, inoveUtils.getNullPropertyNames(escolaUpdate));
        return escolaRespository.save(escola);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try{
            Escola escola = findById(id);
            escolaRespository.delete(escola);
        } catch (DataIntegrityViolationException ex){
            throw new ResourceInUseException("O usuário de ID %d esta em uso e não pode ser removido.");
        }
    }
}
