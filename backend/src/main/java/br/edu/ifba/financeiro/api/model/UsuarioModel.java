package br.edu.ifba.financeiro.api.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;

	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "dt_nasc")
	private Timestamp dt_nasc;

	@Column(name = "senha", nullable = false)
	private String senha;
    
	public UsuarioModel() {
		super();
	}

	public UsuarioModel(String cpf, String nome, String senha) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
	}

	public UsuarioModel(Long id, String cpf, String nome, Timestamp dt_nasc, String senha) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dt_nasc = dt_nasc;
		this.senha = senha;
	}
}
