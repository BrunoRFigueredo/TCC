import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'app/services/login/service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FormGroup, Validators, FormBuilder, FormControl } from '@angular/forms';
import Swal from 'sweetalert2';


@Component({
    selector: 'app-orc-registro',
    templateUrl: './registro.component.html',
    styleUrls:['./registro.component.css']

  })
  
export class DadoTatComponent implements OnInit{


  user:any;
  dados:any;
  editForm:any;
  userID:any;
  dadoID:any;
  
  constructor(private formBuilder: FormBuilder,private router: Router,private apiService: ApiService,private http: HttpClient) {

    if(!window.sessionStorage.getItem('token')) {
      this.router.navigate(['login']);
      Swal.fire('Página inacessivel','Você precisa logar para acessar essa página!','warning');
      return;
    }

  }


  ngOnInit() {
    if(!window.sessionStorage.getItem('token')){
      this.router.navigate(['login']);
      alert("Você precisa estar logado para fazer o orçamento!")
      return;
    }
    this.apiService.current()
    .subscribe( data => {
      console.log(data)
        this.user = data; 
        const options = {
          params: new HttpParams().set('tatuador', this.user.tatuador.id)
        };
        this.http.get('http://localhost:8080/api/dados/tatuador', options)
        .subscribe( dado => {
          console.log(dado)
            this.dados = dado; 
      }, error => {
      console.log(error);
      });
    },
    (erro) => {  
     Swal.fire("Sessão expirada","","error");
     this.router.navigate(['login']);
    }); 

this.editForm = this.formBuilder.group({
  id:'',
  valorTatuagem:['', Validators.required],
  dataTat:['' ,Validators.required],
  descricao:['' ,Validators.required]
})   

}



showModal(idcli,iddado){
  if(!window.sessionStorage.getItem('token')) {
    this.router.navigate(['login']);
    Swal.fire('Página inacessivel','Você precisa logar para acessar essa página!','warning');
    return;
  }
  this.userID = idcli;
  this.dadoID = iddado;

  this.apiService.current()
  .subscribe( data => {
      this.user = data; 
}, error => {
console.log(error);
});
  
}
OnSubmit() {
const formData = new FormData();
formData.append('dado', this.dadoID);
formData.append('tat', this.user.tatuador.id);
formData.append('cli', this.userID);
formData.append('orcamento',JSON.stringify(this.editForm.value))

  this.http.post('http://localhost:8080/api/orcamentos/insert',formData).subscribe(response => {
        Swal.fire("Orçamento enviado com sucesso!","","success");
        document.getElementById("butu").setAttribute('data-dismiss','modal');
      },
      (erro) => {  
       
        console.log(erro)
       Swal.fire("Erro ao enviar os dados!",erro.error.message || erro.error,"error");

      });  

  

}

  
}