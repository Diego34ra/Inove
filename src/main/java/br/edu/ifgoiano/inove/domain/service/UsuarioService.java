package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.UserOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.DiscenteOutputDTO;
import br.edu.ifgoiano.inove.domain.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> list();

    Usuario findById(Long id);

    Usuario create (Usuario newUser);

    Usuario create (Long schoolId, Usuario newUser);

    Usuario update (Long id, Usuario user);

    void deleteById(Long Id);

    List<Usuario> listUserByRole(String role);

    List<UserOutputDTO> listAdmins();

    List<DiscenteOutputDTO> listStudents();

    List<UserOutputDTO> listInstructors();
}
