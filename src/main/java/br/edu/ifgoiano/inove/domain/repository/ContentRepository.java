package br.edu.ifgoiano.inove.domain.repository;

import br.edu.ifgoiano.inove.domain.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content,Long> {
    List<Content> findBySectionId(Long sectionId);
    Optional<Content> findByIdAndSectionId(Long contentId, Long sectionId);
}
