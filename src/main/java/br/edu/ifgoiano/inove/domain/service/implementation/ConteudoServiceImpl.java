package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Conteudo;
import br.edu.ifgoiano.inove.domain.model.Curso;
import br.edu.ifgoiano.inove.domain.repository.ConteudoRepository;
import br.edu.ifgoiano.inove.domain.service.ConteudoService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConteudoServiceImpl implements ConteudoService {

    @Autowired
    private ConteudoRepository conteudoRepository;

    @Autowired
    private InoveUtils utils;

    @Override
    public Conteudo create(Conteudo conteudo) {
        return conteudoRepository.save(conteudo);
    }

    @Override
    public List<Conteudo> findAll() {
        return conteudoRepository.findAll();
    }

    @Override
    public Conteudo findById(Long id) {
        return conteudoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum conteudo com esse id."));
    }

    @Override
    public Conteudo update(Long id, Conteudo conteudo) {
        Conteudo conteudoUpdate = findById(id);
        BeanUtils.copyProperties(conteudoUpdate, conteudo, utils.getNullPropertyNames(conteudoUpdate));
        return conteudoRepository.save(conteudoUpdate);
    }

    @Override
    public void delete(Long id) {
        conteudoRepository.deleteById(id);
    }
}
