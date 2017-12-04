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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataDevolucao == null) ? 0 : dataDevolucao.hashCode());
		result = prime * result + ((dataEmprestimo == null) ? 0 : dataEmprestimo.hashCode());
		result = prime * result + ((emprestador == null) ? 0 : emprestador.hashCode());
		result = prime * result + ((emprestante == null) ? 0 : emprestante.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((objeto == null) ? 0 : objeto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimo other = (Emprestimo) obj;
		if (dataDevolucao == null) {
			if (other.dataDevolucao != null)
				return false;
		} else if (!dataDevolucao.equals(other.dataDevolucao))
			return false;
		if (dataEmprestimo == null) {
			if (other.dataEmprestimo != null)
				return false;
		} else if (!dataEmprestimo.equals(other.dataEmprestimo))
			return false;
		if (emprestador == null) {
			if (other.emprestador != null)
				return false;
		} else if (!emprestador.equals(other.emprestador))
			return false;
		if (emprestante == null) {
			if (other.emprestante != null)
				return false;
		} else if (!emprestante.equals(other.emprestante))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (objeto == null) {
			if (other.objeto != null)
				return false;
		} else if (!objeto.equals(other.objeto))
			return false;
		return true;
	}
	
	
}
