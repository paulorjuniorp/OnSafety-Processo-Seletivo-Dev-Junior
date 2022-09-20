package com.paulorjuniorp.projectonsafety.peoplemanagement.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulorjuniorp.projectonsafety.peoplemanagement.entities.Pessoa;

public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty(message = "O campo Nome é requerido!")
	private String nome;
	@NotEmpty(message = "O campo CPF é requerido!")
	@JsonFormat(pattern = "d{3}.d{3}.d{3}-d{2}")
	@CPF
	private String cpf;
	@NotNull(message = "O campo Data de Nascimento é requerido!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	@NotEmpty(message = "O campo E-mail é requerido!")
	@Email
	private String email;

	public PessoaDTO() {
		super();
	}

	public PessoaDTO(Pessoa pessoa) {
		super();
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.dataNascimento = pessoa.getDataNascimento();
		this.email = pessoa.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
