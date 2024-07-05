package br.edu.ifgoiano.inove.repository;

import br.edu.ifgoiano.inove.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscolaRespository extends JpaRepository<Escola,Long> {
}
