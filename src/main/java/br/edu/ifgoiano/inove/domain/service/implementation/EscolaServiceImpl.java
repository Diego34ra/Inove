package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.exceptions.EntityNotFoundException;
import br.edu.ifgoiano.inove.controller.exceptions.EscolaInUseException;
import br.edu.ifgoiano.inove.controller.exceptions.EscolaNotFoundException;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Escola;
import br.edu.ifgoiano.inove.domain.repository.EscolaRespository;
import br.edu.ifgoiano.inove.domain.service.EscolaService;
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
        BeanUtils.copyProperties(escolaUpdate, escola, getNullPropertyNames(escolaUpdate));
        return escolaRespository.save(escola);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try{
            Escola escola = findById(id);
            escolaRespository.delete(escola);
        } catch (DataIntegrityViolationException ex){
            throw new EscolaInUseException(id);
        }
    }
}
