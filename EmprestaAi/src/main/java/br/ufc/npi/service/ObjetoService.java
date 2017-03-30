package br.ufc.npi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.beans.Objeto;
import br.ufc.npi.repositorio.ObjetoRepositorio;

@Service
public class ObjetoService {
	
	@Autowired
	private ObjetoRepositorio objetoRepo;
		
	public void salvarObjeto(Objeto objeto){
		objetoRepo.save(objeto);
	}
}
