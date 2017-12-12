package br.ufc.npi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.beans.Usuario;
import br.ufc.npi.repositorio.UsuarioRepositorio;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorio usuarioRepo;

	public void salvarUsuario(Usuario usuario) {
		this.usuarioRepo.save(usuario);
	}

	public Usuario buscarUsuario(Integer id) {
		return this.usuarioRepo.findOne(id);
	}

	@Transactional
	public Usuario buscarUsuarioPorNome(String nome) {
		return this.usuarioRepo.findUsuarioByNome(nome);
	}

	public List<Usuario> buscarTodosUsuarios() {
		return this.usuarioRepo.findAll();
	}

	public List<Usuario> buscarTodosUsuariosExceto(Integer id) {
		return this.usuarioRepo.buscarTodosUsuariosExceto(id);
	}
}
