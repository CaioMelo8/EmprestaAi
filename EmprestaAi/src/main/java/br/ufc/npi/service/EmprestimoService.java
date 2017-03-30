package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.beans.Emprestimo;
import br.ufc.npi.repositorio.EmprestimoRepositorio;

@Service
public class EmprestimoService {
	
	@Autowired
	private EmprestimoRepositorio emprestimoRepo;
	
	public void salvarEmprestimo(Emprestimo emprestimo){
		emprestimoRepo.save(emprestimo);
	}
	
	public Emprestimo buscarEmprestimo(Integer id){
		return emprestimoRepo.findOne(id);
	}
	
	public List<Emprestimo> buscarTodosEmprestimos(){
		return emprestimoRepo.findAll();
	}
	
	public List<Emprestimo> buscarEmprestimosPorEmprestador(Integer idEmprestador){
		return emprestimoRepo.findEmprestimosByEmprestador(idEmprestador);
	}
}
