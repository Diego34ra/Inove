package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.schoolDTOs.SchoolOutputDTO;
import br.edu.ifgoiano.inove.domain.model.School;

import java.util.List;

public interface SchoolService {
    List<SchoolOutputDTO> list();

    SchoolOutputDTO findOneById(Long id);

    School findById(Long id);

    School create (School newEscola);

    School update (Long id, School escola);

    void deleteById(Long Id);
}
