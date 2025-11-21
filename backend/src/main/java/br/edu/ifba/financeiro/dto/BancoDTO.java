package br.edu.ifba.financeiro.dto;

import java.io.Serializable;

public class BancoDTO implements Serializable {
	
	private Long id_banco;
	private Integer codigo;
	private String nome;
	private String url;


	public BancoDTO() {
		super();
	}

	public BancoDTO(Integer codigo, String nome, String url) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.url = url;
	}

	public BancoDTO(Long id_banco, Integer codigo, String nome, String url) {
		super();
		this.id_banco = id_banco;
		this.codigo = codigo;
		this.nome = nome;
		this.url = url;
	}


	public Long getId_banco() {
		return id_banco;
	}
	public void setId_banco(Long id_banco) {
		this.id_banco = id_banco;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

	

}
