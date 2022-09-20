import { Pessoa } from 'src/app/models/pessoa';
import { Component, OnInit } from '@angular/core';
import { PessoaService } from 'src/app/services/pessoa.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-pessoa-delete',
  templateUrl: './pessoa-delete.component.html',
  styleUrls: ['./pessoa-delete.component.css']
})
export class PessoaDeleteComponent implements OnInit {

  idPessoa = ''

  pessoa: Pessoa = {
    id:'',
    nome:'',
    cpf:'',
    dataNascimento:'',
    email:''
  };

  constructor(private router: Router, private service : PessoaService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.idPessoa = this.route.snapshot.paramMap.get('id')!;
    this.findById();
  };

  findById(): void{
    this.service.findById(this.idPessoa).subscribe(resposta => {
      this.pessoa = resposta;
    })
  }

  delete():void{
    this.service.delete(this.idPessoa).subscribe(resposta => {
      this.router.navigate(['pessoas']);
      this.service.message('Pessoa deletada com sucesso!');
    })
  }

  cancel(): void{
    this.router.navigate(['pessoas']);
  }

}
