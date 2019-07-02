import { Component, OnInit , Inject} from '@angular/core';
import {Router} from "@angular/router";

import { ApiService } from 'app/services/login/service';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ImageService } from 'app/services/image/image';
import { FormGroup, Validators, FormBuilder, FormControl } from '@angular/forms';
import Swal from 'sweetalert2';


@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls:['./home.component.css']

  })
  
  export class HomeComponent implements OnInit {

    users: any;
    image: HTMLImageElement;
    imageToShow: any;
    isImageLoading: boolean;
    one:any;
    fileData: File = null;
    user:any;
    userID:any;
    editForm: FormGroup;
    foto:any;
    coments:any;
    
    constructor(private formBuilder: FormBuilder,private router: Router, private apiService: ApiService,private http: HttpClient,private imageService: ImageService) {

 
    }
  
       
    ngOnInit() {
      this.apiService.getTatuadores()
        .subscribe( data => {
            this.users = data;
           
    }, error => {
      console.log(error);
    }); 

    this.apiService.current()
    .subscribe( data => {
        this.user = data; 
}, error => {
  console.log(error);
});
    this.editForm = this.formBuilder.group({
      id:'',
      lugarTat:['', Validators.required],
      comprimentoTat:['', Validators.required],
      comprimentoLugar:['',Validators.required],
      dataTat:['' ,Validators.required],
      descricao:['' ,Validators.required]
    })
  }
  onFileChange(event) {
    if (event.target.files.length > 0) {
    this.foto = event.target.files[0];
    }
  }



    showModal(id){
      if(!window.sessionStorage.getItem('token')) {
        this.router.navigate(['login']);
        Swal.fire('Página inacessivel','Você precisa logar para acessar essa página!','warning');
        return;
      }
     
      this.userID = id;

      this.apiService.current()
      .subscribe( data => {
          this.user = data; 
  }, error => {
    Swal.fire("Página inacessivel!","Sessão expirada!","question")
    this.router.navigate(['login']);
  });
      
  }
 
  OnSubmit() {
   
  if(this.foto === undefined){
   
    Swal.fire('O campo da imagem está vazio!','Envie uma imagem da sua tatuagem para o tatuador!','warning');
    return;

  }else{
    

  
    const formData = new FormData();
    formData.append('file', this.foto);
    formData.append('cli', this.user.id);
    formData.append('tat', this.userID);
    formData.append('dado',JSON.stringify(this.editForm.value))
    const headers = {
      'Authorization': 'Bearer ' + JSON.parse(window.sessionStorage.getItem('token')).access_token
    }
    document.getElementById("butu").setAttribute('data-dismiss','modal');
    this.http.post('http://localhost:8080/api/dados/uploadFile',formData,{headers}).subscribe(response => {
        Swal.fire("Dados enviados com sucesso!","","success");
        
      },
      (erro) => {  
       
        console.log(erro)
       Swal.fire("Erro ao enviar os dados!",erro.error.message || erro.error,"error");

      });  

  }
    
    
  
  }
  showComents(id){

    const options = {
      params: new HttpParams().set('tat', id)
    };

    this.http.get('http://localhost:8080/api/coments/tatuador', options).subscribe(data =>{
      console.log(data);
      this.coments = data; 
  }, error => {
    console.log(error);
  }); 

  }

  
 

  }
    
   

    
    

  