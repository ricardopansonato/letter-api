package br.com.fiap.api.android.trabalho.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fiap.api.android.trabalho.model.Letter;

public interface LetterRepository extends MongoRepository<Letter, String> {

}
