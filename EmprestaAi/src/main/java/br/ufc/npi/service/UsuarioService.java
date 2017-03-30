package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.beans.Usuario;
import br.ufc.npi.repositorio.UsuarioRepositorio;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepositorio usuarioRepo;
	
	public void salvarUsuario(Usuario usuario){		
		usuarioRepo.save(usuario);
	}
	
	public Usuario buscarUsuario(Integer id){
		return usuarioRepo.findOne(id);
	}
	
	public List<Usuario> buscarTodosUsuarios(){
		return usuarioRepo.findAll();
	}
}
