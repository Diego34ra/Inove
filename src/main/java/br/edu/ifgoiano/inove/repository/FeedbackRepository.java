package br.edu.ifgoiano.inove.repository;

import br.edu.ifgoiano.inove.model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, Long> {
}
