package br.ufc.npi.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Objeto implements Serializable {

	private static final long serialVersionUID = 2625741077184273545L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private String descricao;

	@OneToOne
	private Usuario usuarioDono;

	@OneToOne
	private Emprestimo emprestimo;

	public final Integer getId() {
		return this.id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getNome() {
		return this.nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	public final String getDescricao() {
		return this.descricao;
	}

	public final void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public final Usuario getUsuarioDono() {
		return this.usuarioDono;
	}

	public final void setUsuarioDono(Usuario usuarioDono) {
		this.usuarioDono = usuarioDono;
	}

	public final Emprestimo getEmprestimo() {
		return this.emprestimo;
	}

	public final void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
}
