package br.edu.ifba.financeiro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import br.edu.ifba.financeiro.model.BancoModel;
import br.edu.ifba.financeiro.repository.BancoRepository;


@RestController
@RequestMapping("/banco")
public class BancoController {
	
	private BancoRepository bancoRepository;
	
	public BancoController(BancoRepository bancoRepository) {
		super();
		this.bancoRepository = bancoRepository;
	}

	
	// Método que retornar todos os bancos do banco de dados
	@GetMapping("/listall")
	public List<BancoModel> listall() {
		var bancos = bancoRepository.findAll();
		return bancos;
	}

	// Método que retornar o banco associado ao ID passado como parametro
	@GetMapping("/findById/{id}")
    public BancoModel findById(@PathVariable("id") Long id) {
		Optional<BancoModel> banco = bancoRepository.findById(id);
		if ( banco.isPresent() )
			return banco.get();
        return null;
    }

	// Método para adicionar um novo usuário
	@PostMapping
	public ResponseEntity<BancoModel> addBanco(@RequestBody BancoModel usuario) {
		BancoModel savedUsuario = bancoRepository.save(usuario);
		return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
	}
	
	// Método para atualizar um banco existente
	@PutMapping("/{id}")
	public ResponseEntity<BancoModel> atualizarBanco(@PathVariable Long id, @RequestBody BancoModel bancoAtualizado) {
		System.out.println("Atualizando banco com ID: " + id);
		System.out.println("Dados recebidos para atualização: " + bancoAtualizado);
		Optional<BancoModel> bancoExistente = bancoRepository.findById(id);

		if (bancoExistente.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		BancoModel banco = bancoExistente.get();
		banco.setNome(bancoAtualizado.getNome());
		banco.setCodigo(bancoAtualizado.getCodigo());
		banco.setUrl(bancoAtualizado.getUrl());

		BancoModel bancoSalvo = bancoRepository.save(banco);
		return new ResponseEntity<>(bancoSalvo, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BancoModel> deleteBanco(@PathVariable Long id) {
		Optional<BancoModel> banco = bancoRepository.findById(id);
		
		if (banco.isPresent()) {
			bancoRepository.delete(banco.get());
			return new ResponseEntity<>(banco.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
