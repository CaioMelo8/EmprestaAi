package br.ufc.npi.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 6860378186800633718L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Email
	private String email;
	private String nome;
	private String endereco;
	private String telefone;
	private String senha;
	private String role;

	private boolean ativo;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "emprestador")
	private List<Emprestimo> emprestimosEmprestador;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "emprestante")
	private List<Emprestimo> emprestimosEmprestante;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Objeto> objetos;

	public final Integer getId() {
		return this.id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getEmail() {
		return this.email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getNome() {
		return this.nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	public final String getSenha() {
		return this.senha;
	}

	public final void setSenha(String senha) {
		this.senha = senha;
	}

	public final List<Objeto> getObjetos() {
		return this.objetos;
	}

	public final void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

	public final String getEndereco() {
		return this.endereco;
	}

	public final void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public final String getTelefone() {
		return this.telefone;
	}

	public final void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public final String getRole() {
		return this.role;
	}

	public final void setRole(String role) {
		this.role = role;
	}

	public final boolean isAtivo() {
		return this.ativo;
	}

	public final void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public final List<Emprestimo> getEmprestimosEmprestador() {
		return this.emprestimosEmprestador;
	}

	public final void setEmprestimosEmprestador(List<Emprestimo> emprestimosEmprestador) {
		this.emprestimosEmprestador = emprestimosEmprestador;
	}

	public final List<Emprestimo> getEmprestimosEmprestante() {
		return this.emprestimosEmprestante;
	}

	public final void setEmprestimosEmprestante(List<Emprestimo> emprestimosEmprestante) {
		this.emprestimosEmprestante = emprestimosEmprestante;
	}
}
