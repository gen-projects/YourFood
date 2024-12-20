package com.generation.yourfood.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Id do usuario", example = "1")
	private Long id;
	
	@NotBlank(message ="Atributo nome não pode ser Vazio")
	@Schema(description = "Nome do usuario", example = "Maria Joaquina")
	private String nome;
	
	@NotBlank(message = "Atributo Usuario é Obrigatorio!!")
	@Email(message = "Atributo Usuario deve ser um Email  Valido!!")
	@Schema(description = "Email do usuario", example = "MaJo@email.com")
	private String usuario;
	
	@NotBlank(message = "Atributo Senha é Obrigatorio!!")
	@Size(min = 8, message = "A senha deve conter no minimo 8 caracteres!!")
	@Schema(description = "Senha do usuario", example = "Cirilo123")
	private String senha;
	
	@Size(max = 5000, message = "O link da foto não pode ser maior que 5000 caracteres")
	@Schema(description = "Foto do usuario", example = "https://i.imgur.com/Tk9f10k.png")
	private String foto;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produto;
	
	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public Usuario(Long id, String  nome, String usuario, String senha, String foto) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.foto = foto;
	}
	
	public Usuario() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
		
}
