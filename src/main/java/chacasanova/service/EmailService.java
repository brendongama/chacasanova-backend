package chacasanova.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${support.mail")
	private String supportMail;
	
	public void sendEmailToCllient(String subject, String email, String content) throws MessagingException {
		
		MimeMessage mail = mailSender.createMimeMessage();
		
		MimeMessageHelper message = new MimeMessageHelper(mail);
		message.setSubject(subject);
		message.setText(content, true);
		message.setFrom(supportMail);
		message.setTo(email);
		
		mailSender.send(mail);
	}
	
	public String getContentMail(String name, String gift) {
		return "<p> Oi, seu amigo(a) " + name.toUpperCase() + " te enviou o presente " + gift.toUpperCase()+" .";
	}
}
