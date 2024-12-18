package com.generation.yourfood.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.yourfood.model.Produto;
import com.generation.yourfood.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtosRepository;
	
	public List<Produto> recomendacao() {
		List<Produto> listaSaudaveis = new ArrayList<Produto>();
		
		Random randomNumbers = new Random();
		
		List<Produto> produtos = produtosRepository.findByCategoria_SaudavelTrue();
		
		if(produtos.size() <= 3) {
			return produtos;
		}
		
		while(listaSaudaveis.size() < 3) {
			int random = randomNumbers.nextInt(produtos.size());
			
			listaSaudaveis.add(produtos.get(random));
			produtos.remove(random);
		}
		
		return listaSaudaveis;
	}
 	
}
