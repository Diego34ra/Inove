package br.edu.ifgoiano.inove.domain.repository;

import br.edu.ifgoiano.inove.domain.model.Conteudo;
import br.edu.ifgoiano.inove.domain.model.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo,Long> {
    List<Conteudo> findBySecaoId(Long sectionId);
    Optional<Conteudo> findByIdAndSecaoId(Long contentId, Long sectionId);
}
