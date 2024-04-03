package com.br.cosmosdb.mongoapi.crud.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.cosmosdb.mongoapi.crud.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	public Optional<Usuario> findByEmailId(String emailId);
	
}
