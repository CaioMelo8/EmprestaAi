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
	public Emprestimo findEmprestimoByEmprestador(Usuario emprestador);
	public Emprestimo findEmprestimoByEmprestante(Usuario emprestante);
	
	@Query(value = "select * from Emprestimo where emprestador_id = ?1", nativeQuery = true)
	public List<Emprestimo> findEmprestimosByEmprestador(Integer idEmprestador);
	
	@Query(value = "select * from Emprestimo where emprestante_id = ?1", nativeQuery = true)
	public List<Emprestimo> findEmprestimosByEmprestante(Integer idEmprestante);
}
