package br.edu.ifba.financeiro.api.controller;

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

import br.edu.ifba.financeiro.api.dto.LoginRequestDTO;
import br.edu.ifba.financeiro.api.dto.UsuarioDTO;
import br.edu.ifba.financeiro.api.model.UsuarioModel;
import br.edu.ifba.financeiro.api.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private UsuarioRepository usuRepository;
	
	public UsuarioController(UsuarioRepository usuRepository) {
		super();
		this.usuRepository = usuRepository;
	}

	@GetMapping
	public String teste() {
		return "Testando Rota Usuario";
	}
	
	// Método que retornar todos os usuarios do banco de dados
	@GetMapping("/listall")
	public List<UsuarioModel> listall() {
		var usuarios = usuRepository.findAll();
		return usuarios;
	}

	// Método que retornar o usuario associado ao ID passado como parametro
	@GetMapping("/findById/{id}")
    public UsuarioModel findById(@PathVariable("id") Long id) {
		Optional<UsuarioModel> usuario = usuRepository.findById(id);
		if ( usuario.isPresent() )
			return usuario.get();
        return null;
    }

	// Método que retornar o usuario a partir d CPF
	@GetMapping("/findByCpf/{cpf}")
    public UsuarioModel findById(@PathVariable("cpf") String cpf) {
		Optional<UsuarioModel> usuario = usuRepository.findByCpf(cpf);
		if ( usuario.isPresent() )
			return usuario.get();
        return null;
    }

	// Método para adicionar um novo usuário
	@PostMapping
	public ResponseEntity<UsuarioModel> addUsuario(@RequestBody UsuarioModel usuario) {
		UsuarioModel savedUsuario = usuRepository.save(usuario);
		return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
	}

	@PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<UsuarioModel> usuario = usuRepository.findByCpf(loginRequestDTO.getLogin());

		boolean isAuthorized = usuario.isPresent() && usuario.get().getSenha().equals(loginRequestDTO.getSenha());
		if (isAuthorized) {
			UsuarioModel usuModel = usuario.get();
			UsuarioDTO user = new UsuarioDTO(
				usuModel.getId(),
				usuModel.getCpf(),
				usuModel.getNome(),
				usuModel.getDt_nasc()
			);
			return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

	@DeleteMapping("/{id_usario}")
	public ResponseEntity<UsuarioModel> deleteUsuario(@PathVariable Long id_usario) {
		Optional<UsuarioModel> usuario = usuRepository.findById(id_usario);
		
		if (usuario.isPresent()) {
			usuRepository.delete(usuario.get());
			return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}