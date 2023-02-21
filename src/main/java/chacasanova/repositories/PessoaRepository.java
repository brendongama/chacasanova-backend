package chacasanova.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import chacasanova.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	
}
