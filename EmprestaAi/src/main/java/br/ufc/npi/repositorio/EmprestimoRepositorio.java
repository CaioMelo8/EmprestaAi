package br.ufc.npi.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.npi.beans.Emprestimo;
import br.ufc.npi.beans.Usuario;

@Repository
@Transactional
public interface EmprestimoRepositorio extends JpaRepository<Emprestimo, Integer> {	
	@Query("from Emprestimo e where e.emprestador = ?1")
	public List<Emprestimo> findEmprestimosByEmprestador(Usuario emprestador);
	
	@Query("from Emprestimo e where e.emprestante = ?1")
	public List<Emprestimo> findEmprestimosByEmprestante(Usuario emprestante);
}
