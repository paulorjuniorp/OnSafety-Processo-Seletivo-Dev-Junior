package com.paulorjuniorp.projectonsafety.peoplemanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paulorjuniorp.projectonsafety.peoplemanagement.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("SELECT pessoaObj FROM Pessoa pessoaObj WHERE pessoaObj.cpf =:cpf")
	Pessoa findByCPF(@Param("cpf") String cpf);

	List<Pessoa> findByNomeContaining(@Param("nome") String nome);
	
}
