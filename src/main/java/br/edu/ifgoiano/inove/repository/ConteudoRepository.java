package br.edu.ifgoiano.inove.repository;

import br.edu.ifgoiano.inove.model.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo,Long> {
}
