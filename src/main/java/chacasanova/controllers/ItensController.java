package chacasanova.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chacasanova.models.Itens;
import chacasanova.service.ItensService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/itens")
public class ItensController {
	
	@Autowired
	private ItensService service;
	
	@GetMapping
	public ResponseEntity<List<Itens>> getAllItens() {
		List<Itens> itens = service.getItens();
		List<Itens> itensAtivos = new ArrayList<>();
		for(var item : itens) {
			item.getAtivo();
			if(item.getAtivo()) {
				itensAtivos.add(item);
			}			
		}
		return ResponseEntity.ok().body(itensAtivos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Itens> buscarItem(@PathVariable Long id){
		Optional<Itens> bolo = service.buscarItem(id);
		return ResponseEntity.ok().body(bolo.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Itens> atualizarItemAtivo(@RequestBody Itens item, @PathVariable Long id) {
		Optional<Itens> oldItem = service.buscarItem(id);
		if(oldItem.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		item.setId(id);
		Itens newItem = service.save(item);
		return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
	}

}
