package br.edu.ifgoiano.inove.domain.repository;

import br.edu.ifgoiano.inove.domain.model.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecaoRepository extends JpaRepository<Secao,Long> {
    List<Secao> findByCursoId(Long courseId);
    Optional<Secao> findByIdAndCursoId(Long sectionId, Long courseId);
}
