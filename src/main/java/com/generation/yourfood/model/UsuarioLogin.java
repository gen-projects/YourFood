package com.generation.yourfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioLogin {
	
	@JsonIgnore
	@Schema(description = "Id do usuario", example = "1")
	private Long id;
	
	@JsonIgnore
	@Schema(description = "Nome do usuario", example = "Maria Joaquina")
	private String nome;
	
	@Schema(description = "Email do usuario", example = "MaJo@email.com")
	private String usuario;
	
	@Schema(description = "Senha do usuario", example = "Cirilo123")
	private String senha;
	
	@JsonIgnore
	@Schema(description = "Foto do usuario", example = "https://i.imgur.com/Tk9f10k.png")
	private String foto;
	
	@Schema(description = "token do usuario", example = "")
	private String token;

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
