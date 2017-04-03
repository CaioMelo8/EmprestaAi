package br.ufc.npi.beans;

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
public class Usuario {

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Emprestimo> getEmprestimosEmprestador() {
		return emprestimosEmprestador;
	}

	public void setEmprestimosEmprestador(List<Emprestimo> emprestimosEmprestador) {
		this.emprestimosEmprestador = emprestimosEmprestador;
	}

	public List<Emprestimo> getEmprestimosEmprestante() {
		return emprestimosEmprestante;
	}

	public void setEmprestimosEmprestante(List<Emprestimo> emprestimosEmprestante) {
		this.emprestimosEmprestante = emprestimosEmprestante;
	}
}
