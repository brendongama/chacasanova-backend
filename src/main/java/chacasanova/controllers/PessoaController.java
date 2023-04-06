package chacasanova.controllers;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chacasanova.models.Pessoa;
import chacasanova.service.EmailService;
import chacasanova.service.PessoaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pessoaItem")


public class PessoaController {
	
	@Autowired
	private PessoaService service;
	
	@Autowired
	private EmailService emailService;	
	
	@Value("${send.mail.brendon}")
	private String sendMailBrendon;
	
	@Value("${send.mail.thalita}")
	private String sendMailThalita;
	
	@PostMapping
	private ResponseEntity<Pessoa> pegarItem(@RequestBody Pessoa pessoaItem ) {
		
		String content = emailService.getContentMail(pessoaItem.getNome(), pessoaItem.getItem());				
		try {
Troc			emailService.sendEmailToCllient("Presente do APARTAMENTO", sendMailThalita, content);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.pegarItem(pessoaItem));
		
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> getAllPessoas() {
		List<Pessoa> pessoas = service.getPessoas();
		return ResponseEntity.ok().body(pessoas);
	}

}
