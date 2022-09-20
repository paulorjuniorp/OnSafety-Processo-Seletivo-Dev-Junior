package com.paulorjuniorp.projectonsafety.peoplemanagement.services;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulorjuniorp.projectonsafety.peoplemanagement.entities.Pessoa;
import com.paulorjuniorp.projectonsafety.peoplemanagement.repositories.PessoaRepository;

@Service
public class DBService {
	@Autowired
	private PessoaRepository pessoaRepository;

	
	public void instantiateDB() {
		Pessoa pessoa = new Pessoa(null,"PersonTest","63646170000",LocalDate.now(),"persontest@springtest.com");
		
		pessoaRepository.saveAll(Arrays.asList(pessoa));
	}
}
