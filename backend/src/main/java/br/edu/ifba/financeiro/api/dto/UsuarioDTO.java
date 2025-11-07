package br.edu.ifba.financeiro.api.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.financeiro.api.model.UsuarioModel;

public class UsuarioDTO implements Serializable {

	private Long id;
	private String cpf;
	private String nome;
	private Timestamp dt_nasc;
	private String senha;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Long id, String cpf, String nome, Timestamp dt_nasc) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dt_nasc = dt_nasc;
	}

	public UsuarioDTO(Long id, String cpf, String nome, Timestamp dt_nasc, String senha) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dt_nasc = dt_nasc;
		this.senha = senha;
	}

	public static UsuarioDTO converter(UsuarioModel usuarioModel) {
		var usuario = new UsuarioDTO();
		usuario.setId( usuarioModel.getId() );
		usuario.setCpf( usuarioModel.getCpf() );
		usuario.setNome( usuarioModel.getNome() );
		usuario.setDt_nasc( usuarioModel.getDt_nasc() );
		usuario.setSenha( usuarioModel.getSenha() );
		return usuario;
	}
	
	public static List<UsuarioDTO> converter(List<UsuarioModel> usuarios) {
		List<UsuarioDTO> list=new ArrayList<UsuarioDTO>();
		
		for (UsuarioModel model : usuarios) {
			list.add( UsuarioDTO.converter(model)) ;
		}
		return list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Timestamp getDt_nasc() {
		return this.dt_nasc;
	}

	public void setDt_nasc(Timestamp dt_nasc) {
		this.dt_nasc = dt_nasc;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
