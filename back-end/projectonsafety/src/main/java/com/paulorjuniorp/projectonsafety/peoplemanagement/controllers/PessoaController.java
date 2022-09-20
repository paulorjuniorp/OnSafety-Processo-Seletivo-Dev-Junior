package com.paulorjuniorp.projectonsafety.peoplemanagement.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paulorjuniorp.projectonsafety.peoplemanagement.dto.PessoaDTO;
import com.paulorjuniorp.projectonsafety.peoplemanagement.entities.Pessoa;
import com.paulorjuniorp.projectonsafety.peoplemanagement.services.PessoaService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Busca e retorna uma pessoa a partir do ID")
	public ResponseEntity<PessoaDTO> findById(@PathVariable Long id){
		PessoaDTO pessoaDTO = new PessoaDTO(pessoaService.findById(id));
		return ResponseEntity.ok().body(pessoaDTO);
	}
	
	@GetMapping(value = "/nome")
	@ApiOperation(value = "Busca e retorna uma pessoa ou uma lista de pessoas a partir de uma parte do nome. Ex.: pessoas/nome/?nome=onsafety")
	public ResponseEntity<List<PessoaDTO>> findByName(@RequestParam(value = "nome") String nome){
		List<Pessoa> listPessoas = pessoaService.findByName(nome);
		List<PessoaDTO> listPessoasDTO = listPessoas.stream().map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listPessoasDTO);
	}
	
	@GetMapping
	@ApiOperation(value = "Busca e retorna uma lista de pessoas cadastradas no banco de dados")
	public ResponseEntity<List<PessoaDTO>> findAll(){
		List<PessoaDTO> pessoaDTOList = pessoaService.findAll().stream()
				.map((objPessoa -> new PessoaDTO(objPessoa)))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(pessoaDTOList);
	}
	
	@PostMapping
	@ApiOperation(value = "Cadastro de uma pessoa no banco de dados")
	public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO pessoaDTO){
		Pessoa novaPessoa = pessoaService.create(pessoaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(novaPessoa.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualização de uma pessoa no banco de dados")
	public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @Valid @RequestBody PessoaDTO pessoaDTO){
		PessoaDTO novaPessoa = new PessoaDTO(pessoaService.update(id,pessoaDTO));
		
		return ResponseEntity.ok().body(novaPessoa);
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Exclusão de uma pessoa no banco de dados")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		pessoaService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
