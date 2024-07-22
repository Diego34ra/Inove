package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.domain.model.Escola;
import br.edu.ifgoiano.inove.domain.model.Usuario;
import br.edu.ifgoiano.inove.domain.model.UsuarioRole;

import java.util.List;

public interface UsuarioService {
    List<Usuario> list();

    Usuario findById(Long id);

    Usuario create (Usuario newUser);

    Usuario update (Long id, Usuario user);

    void deleteById(Long Id);

    List<Usuario> listUserByRole(String role);
}
