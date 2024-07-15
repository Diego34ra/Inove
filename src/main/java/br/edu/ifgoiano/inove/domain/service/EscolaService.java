package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.domain.model.Escola;

import java.util.List;

public interface EscolaService {
    List<Escola> list();

    Escola findById(Long id);

    Escola add (Escola newEscola);

    Escola update (Long id, Escola escola);

    void deleteById(Long Id);
}
