import { Component, OnInit , Inject} from '@angular/core';
import {Router} from "@angular/router";

import { ApiService } from 'app/services/login/service';
import { HttpClient } from '@angular/common/http';
import { ImageService } from 'app/services/image/image';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-profile-user',
  templateUrl: './profile.component.html',
  styleUrls:['./profile.component.css']
})
export class ProfileUserComponent implements OnInit {

  users: any;
  imagens:any;

 
  imageToShow: any;
  isImageLoading: boolean;
  fileName: string;
  imagemPath:any;
  filePreview: string


  constructor(private sanitizer: DomSanitizer,private router: Router, private apiService: ApiService,private http: HttpClient,private imageService: ImageService) {
  }

  ngOnInit() {
    if(!window.sessionStorage.getItem('token')) {
      Swal.fire("Você precisa fazer o login para acessar essa página!","","warning")
      this.router.navigate(['login']);
      return;
    }
    
    this.apiService.current()
      .subscribe( data => {
        console.log(data)
          this.users = data;
      },
      (erro) => {  
       Swal.fire("Sessão expirada","","error");
       this.router.navigate(['login']);
      }); 
    }
    inputFileChange(event) {
      if (event.target.files && event.target.files[0]) {
        const foto = event.target.files[0];
    
        const formData = new FormData();
        formData.append('file', foto);
        formData.append('id',this.users.id)
  
        this.http.post('http://localhost:8080/api/users/uploadFile', formData,this.users.id)
          .subscribe(resposta => Swal.fire("Imagem Alterada!","Por favor relogue para aplicar as alterações!","success"));
        
          }
        
      }

      InputAnyFiles(event) {
        if (event.target.files && event.target.files[0]) {
          const foto = event.target.files[0];
      
          const formData = new FormData();
          formData.append('file', foto);
          formData.append('id',this.users.tatuador.id)
      
          this.http.post('http://localhost:8080/api/tatuador/uploadFile', formData)
            .subscribe(resposta => Swal.fire("Imagem inserida!","Por favor relogue para aplicar as alterações!","success"));
          
            }
          
        }
     
     
    
    
      


    
  }
  








  


  


