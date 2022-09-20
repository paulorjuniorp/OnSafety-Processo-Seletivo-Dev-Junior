import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs'
import { HttpClient } from '@angular/common/http';
import { Pessoa } from '../models/pessoa';
import { MatSnackBar } from '@angular/material/snack-bar';


@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient, private snack: MatSnackBar) { }

  findAll():Observable<Pessoa[]>{
    const url = this.baseUrl + "/pessoas";

    return this.http.get<Pessoa[]>(url);
  }

  findById(id: any):Observable<Pessoa>{
    const url = `${this.baseUrl}/pessoas/${id}`;

    return this.http.get<Pessoa>(url);
  }

  create(pessoa : Pessoa):Observable<Pessoa>{
    const url = this.baseUrl + "/pessoas";

    return this.http.post<Pessoa>(url, pessoa);
  }

  update(pessoa : Pessoa):Observable<Pessoa>{
    const url = `${this.baseUrl}/pessoas/${pessoa.id}`;

    return this.http.put<Pessoa>(url,pessoa);
  }

  delete(id: any):Observable<void>{
    const url = `${this.baseUrl}/pessoas/${id}`;

    return this.http.delete<void>(url);
  }

  message(msg: String):void {
    this.snack.open(`${msg}`,'OK',{
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 5000
    })
  }
}
