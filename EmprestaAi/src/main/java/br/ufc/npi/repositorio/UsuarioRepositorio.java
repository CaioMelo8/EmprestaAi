package br.ufc.npi.repositorio;

import br.ufc.npi.beans.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    @Query("from Usuario u where u.id != ?1")
    public List<Usuario> buscarTodosUsuariosExceto(Integer id);

    public Usuario findUsuarioByNome(String nome);
}
