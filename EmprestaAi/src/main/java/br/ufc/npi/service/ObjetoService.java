package br.ufc.npi.service;

import br.ufc.npi.beans.Objeto;
import br.ufc.npi.repositorio.ObjetoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetoService {

    @Autowired
    private ObjetoRepositorio objetoRepo;

    public void salvarObjeto(Objeto objeto) {
        objetoRepo.save(objeto);
    }

    public void removerObjeto(Objeto objeto) {
        objetoRepo.delete(objeto);
    }

    public Objeto buscarObjeto(Integer id) {
        return objetoRepo.findOne(id);
    }

    public List<Objeto> buscatTodosObjetos() {
        return objetoRepo.findAll();
    }
}
