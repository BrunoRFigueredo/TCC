import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from "./user.model";
import {Observable} from "rxjs/index";
import { Headers, Http, RequestOptions } from '@angular/http';
import { ConfigService } from '../service';

@Injectable()
export class ApiService {

  constructor(private http: HttpClient,private https : Http,
    private configService: ConfigService) { 

/**SETANDO A URL DO SERVIÇO REST QUE VAI SER ACESSADO */


/*ADICIONANDO O JSON NO HEADER */
this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
this.options = new RequestOptions({ headers: this.headers });
}
  
  baseUrl: string = 'http://localhost:8080/api/users';
  private headers:Headers;
  private options:RequestOptions;

  login(loginPayload) {
    const headers = {
      'Authorization': 'Basic Y2hhbGxlbmdlX2NsaWVudDpzZWNyZXQ=' ,
      'Content-type': 'application/x-www-form-urlencoded'
    }
    return this.http.post('http://localhost:8080/' + 'api/oauth/token', loginPayload, {headers});
  }

  current() {
    const headers = {
      'Authorization': 'Bearer ' + JSON.parse(window.sessionStorage.getItem('token')).access_token ,
      'Content-type': 'application/json'
    }
    return this.http.get('http://localhost:8080/' + 'api/users/current', {headers});
  }

  getUsers() {
    return this.http.get(this.baseUrl + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token);
  }

  getUserById(id: number) {
    return this.http.get(this.baseUrl +'/'+id + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token);
  }

  addCliente(user: User){
    return this.https.post(this.baseUrl, JSON.stringify(user),this.options)
    .map(res => res.json());

}


  createUser(user: User){
    return this.http.post(this.baseUrl, JSON.stringify(user));
}

  updateUser(user: User) {
    return this.http.put(this.baseUrl+ "/" + user.id + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, user);
  }

  deleteUser(id: number){
    return this.http.delete(this.baseUrl + id + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token);
  }

  getTatuadores(){
    return this.http.get(this.baseUrl + '/tatuadores');

  }
}
