package com.br.cosmosdb.mongoapi.crud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "usuario")
@Data
public class Usuario {

	@Id
	private String id;
	private String primeiroNome;
	private String sobrenome;
	private String emailId;
	
}
