import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Pessoa } from 'src/app/models/pessoa';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-pessoa-update',
  templateUrl: './pessoa-update.component.html',
  styleUrls: ['./pessoa-update.component.css']
})
export class PessoaUpdateComponent implements OnInit {

  idPessoa = ''

  pessoa: Pessoa = {
    id:'',
    nome:'',
    cpf:'',
    dataNascimento:'',
    email:''
  };

  nome = new FormControl('',[Validators.minLength(3)]);
  cpf = new FormControl('',[Validators.minLength(11)]);
  dataNascimento = new FormControl('',[Validators.required ,Validators.pattern('(?:3[01]|[12][0-9]|0?[1-9])/(?:(?:10|12|0?[13578])/(?:1[8-9]\\d{2}|[2-9]\\d{3}))')]);
  email = new FormControl('', Validators.email);


  constructor(private router: Router, private service : PessoaService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.idPessoa = this.route.snapshot.paramMap.get('id')!;
    this.findById();
  };

  update():void{
    this.service.update(this.pessoa).subscribe(resposta => {
      this.router.navigate(['pessoas']);
      this.service.message('Atualizado com sucesso!');
    }, err => {
      if(err.error.error.match('já cadastrado')){
        this.service.message(err.error.error)
      } else if(err.error.errors[0].message === "número do registro de contribuinte individual brasileiro (CPF) inválido"){
        this.service.message("CPF inválido!")
      }
   })
  }

  findById(): void{
    this.service.findById(this.idPessoa).subscribe(resposta => {
      this.pessoa = resposta;
    })
  }

  cancel(): void{
    this.router.navigate(['pessoas']);
  }

  errorValidNome(){
    if(this.nome.invalid){
      return 'O nome deve ter de 3 a 100 caracteres'
    }
    return false;
  }

  errorValidCPF(){
    if(this.cpf.invalid){
      return 'O CPF deve ter 11 caracteres'
    }
    return false;
  }

  errorValidDataNascimento(){
    if(this.dataNascimento.invalid){
      return 'A data de nascimento deve estar no formato dd/MM/yyyy'
    }
    return false;
  }

  errorValidEmail(){
    if(this.email.invalid){
      return 'O email deve estar no formato nome@dominio.com ou nome@dominio.com.xx'
    }
    return false;
  }

}
