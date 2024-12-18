package com.generation.yourfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.yourfood.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	public List<Produto> findByNomeContainingIgnoreCase(@Param("nome") String Nome);
	
	public List<Produto> findByPrecoGreaterThanEqual(Long preco);
	
	public List<Produto> findByCategoria_SaudavelTrue();

}
