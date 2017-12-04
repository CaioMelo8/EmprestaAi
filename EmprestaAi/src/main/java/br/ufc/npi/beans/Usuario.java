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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emprestimosEmprestador == null) ? 0 : emprestimosEmprestador.hashCode());
		result = prime * result + ((emprestimosEmprestante == null) ? 0 : emprestimosEmprestante.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((objetos == null) ? 0 : objetos.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		Usuario other = (Usuario) obj;
		if (ativo != other.ativo)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emprestimosEmprestador == null) {
			if (other.emprestimosEmprestador != null)
				return false;
		} else if (!emprestimosEmprestador.equals(other.emprestimosEmprestador))
			return false;
		if (emprestimosEmprestante == null) {
			if (other.emprestimosEmprestante != null)
				return false;
		} else if (!emprestimosEmprestante.equals(other.emprestimosEmprestante))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (objetos == null) {
			if (other.objetos != null)
				return false;
		} else if (!objetos.equals(other.objetos))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	
	
}
