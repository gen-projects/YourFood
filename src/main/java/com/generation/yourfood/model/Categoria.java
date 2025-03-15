package com.generation.yourfood.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id da categoria", example = "1")
    private Long id;

    @NotNull(message = "Nome da categoria deve existir")
    @Schema(description = "Categoria de Produtos", example = "Fruta")
    private String descricao;

    @Column(nullable = false)
    @Schema(description = "É saudavel ou não", example = "true")
    private boolean saudavel; 
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<Produto> produto;
    
	@Size(max = 5000, message = "O link da foto não pode ser maior que 5000 caracteres")
	@Schema(description = "Foto da categoria", example = "https://i.imgur.com/Tk9f10k.png")
	private String foto;
	
    public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    public boolean isSaudavel() {
        return saudavel;
    }

    public void setSaudavel(boolean saudavel) {
        this.saudavel = saudavel;
    }

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
    
}

