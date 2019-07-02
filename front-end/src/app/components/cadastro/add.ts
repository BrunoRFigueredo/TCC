import { Component, OnInit } from '@angular/core';
import { Cliente } from 'app/services/cliente/cliente';
import { Router, ActivatedRoute } from '@angular/router';
import { ClienteService } from 'app/services/cliente/ClienteService';
import { Response } from 'app/services/response';
import { Endereco } from 'app/services/endereco/endereco';
import { EnderecoService } from 'app/services/endereco/EnderecoService';
import { User } from 'app/services/login/user.model';
import { ApiService } from 'app/services/login/service';
import Swal from 'sweetalert2';


@Component({
    
    selector: 'app-user',
    templateUrl: './add.html'

  })
  
  export class AddUserComponent{

    
    private titulo:string;
    private user:User = new User();
    constructor(private userService: ApiService,
                private enderecoService: EnderecoService,
                private router: Router,
                private activatedRoute: ActivatedRoute){}

   
 

    salvar():void {

    

        this.userService.addCliente(this.user).subscribe(response => {
            this.user.tatuador = null;
            this.router.navigate(['login']);
             Swal.fire("Usuário cadastrado com sucesso!","","success");
         },
         (erro) => {  
           let responseError = JSON.parse(erro._body); 
          Swal.fire("Erro ao cadastrar um usuário",responseError.message,"error");

         });  

    
    

    }

  }