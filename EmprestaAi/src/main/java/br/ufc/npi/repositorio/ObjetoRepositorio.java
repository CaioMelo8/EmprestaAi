package br.ufc.npi.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.beans.Objeto;

@Repository
@Transactional
public interface ObjetoRepositorio extends JpaRepository<Objeto, Integer>{	
}