package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.domain.model.Secao;
import br.edu.ifgoiano.inove.domain.model.Usuario;

import java.util.List;

public interface SecaoService {
    List<Secao> list();

    Secao findById(Long id);

    Secao create (Secao newSection);

    Secao update (Long id, Secao section);

    void deleteById(Long Id);
}
