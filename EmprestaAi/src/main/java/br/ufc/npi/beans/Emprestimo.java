package br.ufc.npi.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Usuario emprestador;
	
	@OneToOne
	private Usuario emprestante;
	
	@OneToOne
	private Objeto objeto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmprestimo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrega;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getEmprestador() {
		return emprestador;
	}

	public void setEmprestador(Usuario emprestador) {
		this.emprestador = emprestador;
	}

	public Usuario getEmprestante() {
		return emprestante;
	}

	public void setEmprestante(Usuario emprestante) {
		this.emprestante = emprestante;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}	
}
