import { PessoaService } from './../../../../services/pessoa.service';
import { Component,  AfterViewInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Pessoa } from 'src/app/models/pessoa';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pessoa-read',
  templateUrl: './pessoa-read.component.html',
  styleUrls: ['./pessoa-read.component.css']
})
export class PessoaReadComponent implements AfterViewInit {

  pessoas: Pessoa[] = [];

  displayedColumns: string[] = ['id', 'nome', 'cpf', 'dataNascimento','email','action'];
  dataSource = new MatTableDataSource<Pessoa>(this.pessoas);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private service: PessoaService, private router: Router){

  }

  ngAfterViewInit() {
    this.findAll();
  }

  findAll():void{
    this.service.findAll().subscribe((resposta)=>{
      this.pessoas = resposta;
      this.dataSource = new MatTableDataSource<Pessoa>(this.pessoas);
      this.dataSource.paginator = this.paginator;
    });
  }

  navigateToCreate():void{
    this.router.navigate(['pessoas/create'])
  }
}
