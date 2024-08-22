package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.domain.model.School;

import java.util.List;

public interface SchoolService {
    List<School> list();

    School findById(Long id);

    School create (School newEscola);

    School update (Long id, School escola);

    void deleteById(Long Id);
}
