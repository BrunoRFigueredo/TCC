import { Validators, FormGroup, FormBuilder, FormControl } from "@angular/forms";
import { OnInit, Component } from "@angular/core";
import { User } from "app/services/login/user.model";
import { Router } from "@angular/router";
import { ApiService } from "app/services/login/service";
import { HttpClient } from "@angular/common/http";
import { EnderecoService } from "app/services/endereco/EnderecoService";
import { Response } from "app/services/response";
import Swal from "sweetalert2";


@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit.html'
})

export class EditUserComponent implements OnInit {

    user: User;
    users:any;
    editForm: FormGroup;
    img:any;
    constructor(
      private enderecoService: EnderecoService,private formBuilder: FormBuilder,private router: Router, private apiService: ApiService,private http: HttpClient) { }
  
    ngOnInit() {
        if(!window.sessionStorage.getItem('token')) {
          this.router.navigate(['login']);
          return;
        }
        this.apiService.current()
          .subscribe( data => {
            console.log(data)
              this.users = data;
        
        });
        
      
              
    }
  
    onSubmit() {
      this.apiService.updateUser(this.users).subscribe(response => {
         

        let res:Response = <Response>response;
 
        
        if(res.id == 1){
          alert(res.mensagem);
          this.router.navigate(['/login']);
        }
         else{
           Swal.fire("Usuário alterado com sucesso!","Relogue para aplicar as alterações!","success");
         }
       },
       (erro) => {                    
                                       
          alert(erro);
       });
    }
  
  }