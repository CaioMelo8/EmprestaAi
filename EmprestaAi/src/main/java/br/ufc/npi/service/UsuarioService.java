package br.ufc.npi.service;

import br.ufc.npi.beans.Usuario;
import br.ufc.npi.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    public void salvarUsuario(Usuario usuario) {
        usuarioRepo.save(usuario);
    }


    public Usuario buscarUsuario(Integer id) {
        return usuarioRepo.findOne(id);
    }

    @Transactional
    public Usuario buscarUsuarioPorNome(String nome) {
        return usuarioRepo.findUsuarioByNome(nome);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepo.findAll();
    }

    public List<Usuario> buscarTodosUsuariosExceto(Integer id) {
        return usuarioRepo.buscarTodosUsuariosExceto(id);
    }
}
