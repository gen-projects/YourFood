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
		
		List<Produto> produtos = produtosRepository.findAll();
		
		for(int i=0; i<produtos.size(); i++) {
			if(produtos.get(i).getCategoria().isSaudavel()) {
				if(randomNumbers.nextBoolean()) {
					listaSaudaveis.add(produtos.get(i));
				}
			}
			if(listaSaudaveis.size() == 3) break;
		}
		
		return listaSaudaveis;
	}
 	
}
