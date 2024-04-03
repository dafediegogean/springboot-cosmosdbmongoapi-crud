package com.br.cosmosdb.mongoapi.crud.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.cosmosdb.mongoapi.crud.model.Usuario;
import com.br.cosmosdb.mongoapi.crud.repository.UsuarioRepository;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioResource {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@GetMapping
	public List<Usuario> getllAllUsuarios() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioPorId(@PathVariable(value = "id") String id) {
		return usuarioRepository.findById(id).map(value -> new ResponseEntity<>(value, HttpStatus.OK))
					.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/emailId/{emailId}")
	public ResponseEntity<Usuario> getUsuarioPorEmailId(@PathVariable(value = "emailId") String emailId) {
		return usuarioRepository.findByEmailId(emailId).map(value -> new ResponseEntity<>(value, HttpStatus.OK))
					.orElse(ResponseEntity.notFound().build());
	}

	
	@PostMapping("/criar")
	public Usuario criarUsuario(@RequestBody Usuario usuario) {
		
		return usuarioRepository.save(usuario);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable(value = "id") String id, @RequestBody Usuario novoUsuario) {

		Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
		if (usuarioSalvo.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Usuario usuario = usuarioSalvo.get();
		usuario.setEmailId(novoUsuario.getEmailId());
		usuario.setSobrenome(novoUsuario.getSobrenome());
		usuario.setPrimeiroNome(novoUsuario.getPrimeiroNome());
		usuario.setId(id);
		final Usuario updateUser = usuarioRepository.save(usuario);
		return ResponseEntity.ok(updateUser);
	}
	
	@PutMapping("/emailId/{emailId}")
	public ResponseEntity<Usuario> atualizarUsuarioPorEmailId(@PathVariable(value = "emailId") String emailId, @RequestBody Usuario novoUsuario) {

		Optional<Usuario> usuarioSalvo = usuarioRepository.findByEmailId(emailId);
		if (usuarioSalvo.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Usuario usuario = usuarioSalvo.get();
		usuario.setEmailId(novoUsuario.getEmailId());
		usuario.setSobrenome(novoUsuario.getSobrenome());
		usuario.setPrimeiroNome(novoUsuario.getPrimeiroNome());
		usuario.setId(usuarioSalvo.get().getId());
		final Usuario updateUser = usuarioRepository.save(usuario);
		return ResponseEntity.ok(updateUser);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deletarUsuario(@PathVariable(value = "id") String id) {
		Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
		
		Map<String, Boolean> response = new HashMap<>();
		
		if (usuarioSalvo.isEmpty()) {
			response.put("Usuário não encontrado", Boolean.FALSE);
		} else {
			usuarioRepository.delete(usuarioSalvo.get());
			response.put("Usuário removido", Boolean.TRUE);
		}
		return response;
	}
	
}
