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
		
		produtosRepository.findAll().forEach(produto -> {
			randomNumbers.nextInt();
			
			//if(produto.getCategoria().getIsSaudavel()) {
				
				
			//	listaSaudaveis.add(produto);
			//}
		});
	
		
		return listaSaudaveis;
	}
 	
}
