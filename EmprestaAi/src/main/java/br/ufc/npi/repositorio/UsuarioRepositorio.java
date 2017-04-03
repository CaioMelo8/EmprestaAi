package br.ufc.npi.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.npi.beans.Usuario;

@Repository
@Transactional
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
	@Query("from Usuario u where u.id != ?1")
	public List<Usuario> buscarTodosUsuariosExceto(Integer id);
	
	public Usuario findUsuarioByNome(String nome);
}
