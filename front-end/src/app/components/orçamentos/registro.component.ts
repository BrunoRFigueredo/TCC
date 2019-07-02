import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'app/services/login/service';
import { HttpClient, HttpParams } from '@angular/common/http';
import Swal from 'sweetalert2';


@Component({
    selector: 'app-registro',
    templateUrl: './registro.component.html',
    styleUrls:['./registro.component.css']

  })
  
  export class OrcRegistroComponent implements OnInit{


    user:any;
    orcs:any;
    
    constructor(private router: Router,private apiService: ApiService,private http: HttpClient) {

      if(!window.sessionStorage.getItem('token')) {
        this.router.navigate(['login']);
        Swal.fire('Página inacessivel','Você precisa logar para acessar essa página!','warning');
        return;
      }

    }


    ngOnInit() {
      if(!window.sessionStorage.getItem('token')){
        this.router.navigate(['login']);
        Swal.fire('Página inacessivel','Você precisa logar para acessar essa página!','warning');
        return;
      }
      this.apiService.current()
      .subscribe( data => {
        console.log(data)
          this.user = data; 
          const options = {
            params: new HttpParams().set('tatuador', this.user.tatuador.id)
          };
          this.http.get('http://localhost:8080/api/orcamentos/tatuador', options)
          .subscribe( orc => {
            console.log(orc)
              this.orcs = orc; 
            }, (error) => {
              Swal.fire("Página inacessivel!",error.erro,"question")
              this.router.navigate(['login']);
            });
          },
          (erro) => {  
           Swal.fire("Sessão expirada","","error");
           this.router.navigate(['login']);
          }); 
      

   
  }




  }