package br.ufc.npi.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "emprestador")
	private Usuario emprestador;
	
	@ManyToOne
	@JoinColumn(name = "emprestante")
	private Usuario emprestante;
	
	@OneToOne
	private Objeto objeto;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataEmprestimo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataDevolucao;

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

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}	
}
