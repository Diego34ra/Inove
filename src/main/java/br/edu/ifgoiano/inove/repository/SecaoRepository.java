package br.edu.ifgoiano.inove.repository;

import br.edu.ifgoiano.inove.model.Secao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecaoRepository extends JpaRepository<Secao,Long> {
}
