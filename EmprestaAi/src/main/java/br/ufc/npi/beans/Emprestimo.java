package br.ufc.npi.beans;

import java.io.Serializable;
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
public class Emprestimo implements Serializable {

	private static final long serialVersionUID = -5856335718294752768L;

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

	public final Integer getId() {
		return this.id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final Usuario getEmprestador() {
		return this.emprestador;
	}

	public final void setEmprestador(Usuario emprestador) {
		this.emprestador = emprestador;
	}

	public final Usuario getEmprestante() {
		return this.emprestante;
	}

	public final void setEmprestante(Usuario emprestante) {
		this.emprestante = emprestante;
	}

	public final Date getDataEmprestimo() {
		return this.dataEmprestimo;
	}

	public final void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public final Date getDataDevolucao() {
		return this.dataDevolucao;
	}

	public final void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public final Objeto getObjeto() {
		return this.objeto;
	}

	public final void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}
}
