package br.edu.ifba.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.financeiro.model.BancoModel;

@Repository
public interface BancoRepository 
	extends JpaRepository<BancoModel, Long>
{
}
