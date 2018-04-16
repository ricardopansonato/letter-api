package br.com.fiap.api.android.trabalho.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.fiap.api.android.trabalho.controller.LetterRequest;

@Document
public class Letter {
	@Id
	private String id;
	private String email;
	private String fatherName;
	private String childName;
	private String text;

	public Letter() {
		
	}
	
	public Letter(LetterRequest request) {
		this.childName = request.getChildName();
		this.email = request.getEmail();
		this.fatherName = request.getFatherName();
		this.text = request.getText();
	}
	
	public String getEmail() {
		return email;
	}

	public String getFatherName() {
		return fatherName;
	}

	public String getChildName() {
		return childName;
	}

	public String getText() {
		return text;
	}
}
