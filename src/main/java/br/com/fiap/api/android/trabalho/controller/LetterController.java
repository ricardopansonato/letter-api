package br.com.fiap.api.android.trabalho.controller;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.api.android.trabalho.model.Letter;
import br.com.fiap.api.android.trabalho.repository.LetterRepository;

@CrossOrigin(origins = "*")
@RestController
public class LetterController {

	@Autowired
	private LetterRepository repository;

	@Autowired
	public JavaMailSender emailSender;

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<?> save(HttpServletRequest request, @RequestBody LetterRequest letterRequest) {
		final Letter letter = repository.save(new Letter(letterRequest));
		sendEmail(letter);
		return ResponseEntity.ok().build();
	}

	private void sendEmail(Letter letter) {
		try {
			final MimeBodyPart textBodyPart = new MimeBodyPart();
			final StringBuilder sb = new StringBuilder();

			sb.append("<body>Pedido do seu filho!!!<strong><br/>");
			sb.append("<body>Filho:<strong>").append(letter.getChildName()).append("<br/>");
			sb.append("<body>Texto:<strong>").append(letter.getText()).append("<br/>");
			textBodyPart.setContent(sb.toString(), "text/html; charset=utf-8");

			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(textBodyPart);

			final MimeMessage message = emailSender.createMimeMessage();
			InternetAddress iaRecipient = new InternetAddress(letter.getEmail());
			message.setContent(mimeMultipart);
			message.setSubject("[Natal]  Pedido do seu filho, porra!");
			message.setRecipient(Message.RecipientType.TO, iaRecipient);
			emailSender.send(message);

		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
	}
}
