package chacasanova.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chacasanova.models.Itens;

@Repository
public interface ItensRepository extends JpaRepository<Itens, Long> {

}
