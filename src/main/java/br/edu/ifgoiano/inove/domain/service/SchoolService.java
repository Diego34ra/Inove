package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.schoolDTOs.SchoolInputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.schoolDTOs.SchoolOutputDTO;
import br.edu.ifgoiano.inove.domain.model.School;

import java.util.List;

public interface SchoolService {
    List<SchoolOutputDTO> list();

    SchoolOutputDTO findOneById(Long id);

    School findById(Long id);

    School create (SchoolInputDTO newSchoolDTO);

    School update (Long id, SchoolInputDTO schoolDTO);

    void deleteById(Long Id);
}
