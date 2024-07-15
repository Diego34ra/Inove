package br.edu.ifgoiano.inove.domain.repository;

import br.edu.ifgoiano.inove.domain.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscolaRespository extends JpaRepository<Escola,Long> {
}