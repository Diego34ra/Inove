package br.edu.ifgoiano.inove.repository;

import br.edu.ifgoiano.inove.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
