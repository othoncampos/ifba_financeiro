package br.edu.ifba.financeiro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "banco")
public class BancoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_banco")
	private Long id_banco;
	
	@Column(name = "codigo", nullable = true, unique = false)
	private Integer codigo;

	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "url")
	private String url;

	public BancoModel(Integer codigo, String nome, String url) {
		this.codigo = codigo;
		this.nome = nome;
		this.url = url;
	}

	public BancoModel(Long id_banco, Integer codigo, String nome, String url) {
		this.id_banco = id_banco;
		this.codigo = codigo;
		this.nome = nome;
		this.url = url;
	}

	public BancoModel() {}

	

}
