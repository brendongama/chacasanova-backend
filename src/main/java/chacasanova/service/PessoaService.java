package chacasanova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chacasanova.models.Pessoa;
import chacasanova.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	public Pessoa pegarItem(Pessoa pessoa) {
		String convidadoUppercase = pessoa.getNome().toUpperCase();
		String itemUppercase = pessoa.getItem().toUpperCase();	
		pessoa.setItem(itemUppercase);
		pessoa.setNome(convidadoUppercase);
		return repository.save(pessoa);
	}

	public List<Pessoa> getPessoas() {
		return repository.findAll(); 
	}

}
