package com.generation.yourfood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.yourfood.model.Usuario;
import com.generation.yourfood.model.UsuarioLogin;
import com.generation.yourfood.repository.UsuarioRepository;
import com.generation.yourfood.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Usuarios", description = "Operações relacionadas aos usuários")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Operation(
		    summary = "Buscar todos os usuários", 
		    description = "Este endpoint retorna uma lista de todos os usuários em formato JSON.")
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@Operation(
	        summary = "Buscar usuário por ID", 
	        description = "Este endpoint retorna os detalhes de um usuário baseado no seu ID")
	@GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getById(@PathVariable Long id){
		return usuarioRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
		}
	
	@Operation(
		    summary = "Autenticar usuário", 
		    description = "Este endpoint realiza a autenticação de um usuário utilizando suas credenciais (usuário e senha). Retorna um token de autenticação se as credenciais forem válidas, ou um erro 401 (não autorizado) caso contrário.")
	@PostMapping(value = "/logar",
			consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioLogin> autenticarUsuario(@RequestBody Optional<UsuarioLogin> usuarioLogin){
		
		return usuarioService.autenticarUsuario(usuarioLogin)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@Operation(
		    summary = "Cadastrar um novo usuário", 
		    description = "Este endpoint permite o cadastro de um novo usuário no sistema. Recebe os dados do usuário em formato JSON e retorna o usuário cadastrado em caso de sucesso.")
	@PostMapping(value = "/cadastrar",
			consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> postUsuario(@RequestBody @Valid Usuario usuario) {

		return usuarioService.cadastrarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}
	
	@Operation(
		    summary = "Atualizar os dados de um usuário", 
		    description = "Este endpoint permite a atualização dos dados de um usuário existente no sistema. Recebe as novas informações do usuário em formato JSON e retorna os dados atualizados ou um erro 404 (não encontrado) se o usuário não existir.")
	@PutMapping(value = "/atualizar",
			consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		
		return usuarioService.atualizarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}
	
	
}
