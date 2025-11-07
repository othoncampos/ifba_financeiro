package br.edu.ifba.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.financeiro.api.model.UsuarioModel;
import java.util.Optional;

@Repository
public interface UsuarioRepository 
	extends JpaRepository<UsuarioModel, Long>
{
	Optional<UsuarioModel> findByCpf(String cpf);
}
