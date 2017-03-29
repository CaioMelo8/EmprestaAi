package br.ufc.npi.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.beans.Usuario;

@Repository
@Transactional
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
	public Usuario findByNome(String nome);	
}
