package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.beans.Objeto;
import br.ufc.npi.repositorio.ObjetoRepositorio;

@Service
public class ObjetoService {

	@Autowired
	private ObjetoRepositorio objetoRepo;

	public void salvarObjeto(Objeto objeto) {
		this.objetoRepo.save(objeto);
	}

	public void removerObjeto(Objeto objeto) {
		this.objetoRepo.delete(objeto);
	}

	public Objeto buscarObjeto(Integer id) {
		return this.objetoRepo.findOne(id);
	}

	public List<Objeto> buscatTodosObjetos() {
		return this.objetoRepo.findAll();
	}
}
