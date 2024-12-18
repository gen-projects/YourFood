package com.generation.yourfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.generation.yourfood.model.Categoria;
import com.generation.yourfood.repository.CategoriaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Categorias", description = "Operações relacionadas as categorias de produtos")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Operation(
		    summary = "Cadastrar uma nova categoria", 
		    description = "Este endpoint cria uma nova categoria no sistema. Recebe o nome da categoria e retorna a categoria criada em formato JSON.")
	@PostMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    )
	public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaRepository.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @Operation(
		    summary = "Buscar todas as categorias",
		    description = "Este endpoint retorna uma lista de todos as categorias disponíveis no sistema em formato JSON.")
	@GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @Operation(
		    summary = "Buscar categoria por ID", 
		    description = "Este endpoint retorna os detalhes de uma categoria baseado no seu ID. Se a categoria for encontrada, retorna os dados do tema em formato JSON. Caso contrário, retorna um erro 404 (não encontrado).")
	@GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(
		    summary = "Buscar categorias por nome", 
		    description = "Este endpoint retorna uma lista de categorias cujos nomes contêm a palavra-chave fornecida. A busca é case insensitive.")
	@GetMapping(value = "/descricao/{descricao}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Categoria>> getByTitulo(@PathVariable String descricao){
		return ResponseEntity.ok(categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

    @Operation(
		    summary = "Edita categoria por ID", 
		    description = "Este endpoint edita os detalhes de uma categoria baseado no seu ID.")
    @PutMapping(value = "/{id}" ,
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria categoriaAtualizada = categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @Operation(
		    summary = "Deletar uma categoria", 
		    description = "Este endpoint remove uma categoria do sistema pelo seu ID. Se a categoria for encontrada, ela será deletada, caso contrário, retorna um erro 404 (não encontrado).")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        
    	Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if(categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    	
    	categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
