import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
 
import { Endereco } from './endereco';
import { ConfigService } from '../service';

 
@Injectable()
export class EnderecoService {
 
    private baseUrlService:string = '';
    private headers:Headers;
    private options:RequestOptions;
 
    constructor(private http: Http,
                private configService: ConfigService) { 
 
        /**SETANDO A URL DO SERVIÇO REST QUE VAI SER ACESSADO */
        this.baseUrlService = configService.getUrlService() + '/enderecos';
 
        /*ADICIONANDO O JSON NO HEADER */
        this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
        this.options = new RequestOptions({ headers: this.headers });
    }
 
    /**CONSULTA TODAS AS PESSOAS CADASTRADAS */
    getEnderecos(){        
        return this.http.get(this.baseUrlService).map(res => res.json());
    }
 
    /**ADICIONA UMA NOVA PESSOA */
    addEndereco(endereco: Endereco){
 
        return this.http.post(this.baseUrlService, JSON.stringify(endereco),this.options)
        .map(res => res.json());
    }
    /**EXCLUI UMA PESSOA */
    excluirEndereco(id:number){
 
        return this.http.delete(this.baseUrlService + id).map(res => res.json());
    }
 
    /**CONSULTA UMA PESSOA PELO CÓDIGO */
    getEndereco(id:number){
 
        return this.http.get(this.baseUrlService + id).map(res => res.json());
    }
 
    /**ATUALIZA INFORMAÇÕES DA PESSOA */
    atualizarEndereco(endereco:Endereco){
 
        return this.http.put(this.baseUrlService, JSON.stringify(endereco),this.options)
        .map(res => res.json());
    }
 
}