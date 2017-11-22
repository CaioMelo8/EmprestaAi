package br.ufc.npi.repositorio;

import br.ufc.npi.beans.Objeto;
import br.ufc.npi.beans.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ObjetoRepositorio extends JpaRepository<Objeto, Integer> {
    public List<Objeto> findAllByUsuarioDono(Usuario usuarioDono);
}