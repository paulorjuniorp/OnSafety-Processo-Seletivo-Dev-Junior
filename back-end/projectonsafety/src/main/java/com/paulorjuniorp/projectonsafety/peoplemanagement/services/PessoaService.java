package com.paulorjuniorp.projectonsafety.peoplemanagement.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulorjuniorp.projectonsafety.peoplemanagement.dto.PessoaDTO;
import com.paulorjuniorp.projectonsafety.peoplemanagement.entities.Pessoa;
import com.paulorjuniorp.projectonsafety.peoplemanagement.repositories.PessoaRepository;
import com.paulorjuniorp.projectonsafety.peoplemanagement.services.exceptions.DataIntegrationViolationException;
import com.paulorjuniorp.projectonsafety.peoplemanagement.services.exceptions.PersonNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa findById(Long id) {
		Optional<Pessoa> objPessoa = pessoaRepository.findById(id);
		return objPessoa.orElseThrow(() -> new PersonNotFoundException(
				"Pessoa não encontrada! Id:" + id + ", Tipo: " + Pessoa.class.getName()));
	}
	
	public List<Pessoa> findByName(String nome) {
		List<Pessoa> listPessoasObj = pessoaRepository.findByNomeContaining(nome);
		if(listPessoasObj.size() <= 0) {
			throw new PersonNotFoundException("Pessoa não encontrada!" + nome);
		}
		return listPessoasObj;
	}

	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	public Pessoa create(PessoaDTO pessoaDTO) {
		if (findByCPF(pessoaDTO) != null) {
			throw new DataIntegrationViolationException("CPF já cadastrado na base de dados!");
		}

		return pessoaRepository.save(new Pessoa(null, pessoaDTO.getNome(), pessoaDTO.getCpf(),
				pessoaDTO.getDataNascimento(), pessoaDTO.getEmail()));

	}

	public Pessoa update(Long id, @Valid PessoaDTO pessoaDTO) {
		Pessoa oldPessoa = this.findById(id);

		if (findByCPF(pessoaDTO) != null && findByCPF(pessoaDTO).getId() != id) {
			throw new DataIntegrationViolationException("CPF já cadastrado na base de dados!");
		}

		oldPessoa.setNome(pessoaDTO.getNome());
		oldPessoa.setCpf(pessoaDTO.getCpf());
		oldPessoa.setDataNascimento(pessoaDTO.getDataNascimento());
		oldPessoa.setEmail(pessoaDTO.getEmail());

		return pessoaRepository.save(oldPessoa);
	}

	public void delete(Long id) {
		this.findById(id);
		pessoaRepository.deleteById(id);
	}

	private Pessoa findByCPF(PessoaDTO pessoaDTO) {
		Pessoa pessoaObj = pessoaRepository.findByCPF(pessoaDTO.getCpf());

		if (pessoaObj != null) {
			return pessoaObj;
		}
		return null;
	}
}
