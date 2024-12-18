package com.generation.yourfood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.yourfood.model.Produto;
import com.generation.yourfood.repository.ProdutoRepository;
import com.generation.yourfood.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "produtos", description = "Operações relacionadas aos produtos")
public class ProdutoController {

	@Autowired
	public ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoService produtoService;

	@Operation(
		    summary = "Buscar todas os Produtos", 
		    description = "Este endpoint retorna uma lista de todos os Produtos em formato JSON.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@Operation(
		    summary = "Buscar Produto por ID", 
		    description = "Este endpoint retorna os detalhes de um Produto baseado no seu ID. Se o Produto for encontrado, retorna os dados em formato JSON. Caso contrário, retorna um erro 404 (não encontrado).")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@Operation(
		    summary = "Buscar Produtos por nome", 
		    description = "Este endpoint retorna uma lista de Produtos cujos nomes contêm a palavra-chave fornecida. A busca é insensível a maiúsculas e minúsculas.")
	@GetMapping(value = "/nome/{nome}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findByNomeContainingIgnoreCase(nome));
	}

	@Operation(
		    summary = "Buscar Produtos por preço", 
		    description = "Este endpoint retorna uma lista de Produtos cujos preços contêm o valor acima do fornecido.")
	@GetMapping(value = "/preco/{preco}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Produto>> getByPreco(@PathVariable Long preco) {
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanEqual(preco));
	}
	
	
	@Operation(
		    summary = "Buscar recomentações de produtos saudáveis", 
		    description = "Este endpoint retorna uma lista de até 3 produtos saudáveis em formato JSON.")
	@GetMapping(value = "/recomendacoes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Produto>> getRecomendacao() {
		return ResponseEntity.ok(produtoService.recomendacao());
	}

	@Operation(
		    summary = "Criar um novo Produto", 
		    description = "Este endpoint cria um novo Produto. Recebe os dados do Produto em formato JSON e retorna o Produto criada em formato JSON.")
	@PostMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    ) 
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}

	@Operation(
		    summary = "Atualizar um produto", 
		    description = "Este endpoint atualiza os dados de um produto existente. Recebe os dados atualizados em formato JSON e retorna o produto atualizado em formato JSON.")
	@PutMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    ) 
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		return produtoRepository.findById(produto.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@Operation(
		    summary = "Deletar um produto", 
		    description = "Este endpoint remove um produto pelo seu ID. Se o produto for encontrado, ele será deletado. Caso contrário, retorna um erro 404 (não encontrado).")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		produtoRepository.deleteById(id);
	}
}