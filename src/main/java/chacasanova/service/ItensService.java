package chacasanova.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chacasanova.models.Itens;
import chacasanova.repositories.ItensRepository;

@Service
public class ItensService {

	@Autowired
	private ItensRepository itensRepository;
	
	public List<Itens> getItens() {
		return itensRepository.findAll(); 
	}

	public Optional<Itens> buscarItem(Long id) {
		Optional<Itens> item = itensRepository.findById(id);
		return item;
	}

	public Itens save(Itens item) {
		return itensRepository.save(item);
	}
		
}
