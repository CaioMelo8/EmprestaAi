package br.ufc.npi.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.beans.Objeto;
import br.ufc.npi.beans.Usuario;

@Repository
@Transactional
public interface ObjetoRepositorio extends JpaRepository<Objeto, Integer>{
	public Objeto findByNome(String nome);
	public Objeto findByUsuarioDono(Usuario usuarioDono);
}