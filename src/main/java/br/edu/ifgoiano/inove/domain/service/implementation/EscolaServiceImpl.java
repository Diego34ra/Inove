package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.domain.exceptions.EntityNotFoundException;
import br.edu.ifgoiano.inove.domain.exceptions.EscolaInUseException;
import br.edu.ifgoiano.inove.domain.exceptions.EscolaNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Escola;
import br.edu.ifgoiano.inove.domain.repository.EscolaRespository;
import br.edu.ifgoiano.inove.domain.service.EscolaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(()-> new EscolaNotFoundException(id));
    }

    @Override
    @Transactional
    public Escola add(Escola newEscola) {
        return escolaRespository.save(newEscola);
    }

    @Override
    @Transactional
    public Escola update(Long id, Escola escola) {
        try{
            Escola savedEscola = findById(id);
            BeanUtils.copyProperties(escola,savedEscola);
            return escolaRespository.save(savedEscola);
        }catch (EntityNotFoundException ex){
            throw  new EscolaNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try{
            escolaRespository.deleteById(id);
        }catch (DataIntegrityViolationException ex){
            throw new EscolaInUseException(id);
        }
    }
}
