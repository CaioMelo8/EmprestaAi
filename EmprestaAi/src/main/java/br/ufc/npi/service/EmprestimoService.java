package br.ufc.npi.service;

import br.ufc.npi.beans.Emprestimo;
import br.ufc.npi.beans.Usuario;
import br.ufc.npi.repositorio.EmprestimoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepositorio emprestimoRepo;

    public void salvarEmprestimo(Emprestimo emprestimo) {
        emprestimoRepo.save(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimoRepo.delete(emprestimo);
    }

    public Emprestimo buscarEmprestimo(Integer id) {
        return emprestimoRepo.findOne(id);
    }

    public List<Emprestimo> buscarTodosEmprestimos() {
        return emprestimoRepo.findAll();
    }

    public List<Emprestimo> buscarEmprestimosPorEmprestador(Usuario usuario) {
        return emprestimoRepo.findEmprestimosByEmprestador(usuario);
    }
}
