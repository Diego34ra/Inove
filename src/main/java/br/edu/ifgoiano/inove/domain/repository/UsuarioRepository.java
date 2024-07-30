package br.edu.ifgoiano.inove.domain.repository;

import br.edu.ifgoiano.inove.domain.model.Usuario;
import br.edu.ifgoiano.inove.domain.model.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_usuario WHERE tipo = :role")
    List<Usuario> findByTipoContaining(String role);
}
