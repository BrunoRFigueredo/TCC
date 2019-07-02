import { Validators, FormGroup, FormBuilder, FormControl } from "@angular/forms";
import { OnInit, Component } from "@angular/core";
import { User } from "app/services/login/user.model";
import { Router, ActivatedRoute } from "@angular/router";
import { ApiService } from "app/services/login/service";
import { HttpClient } from "@angular/common/http";
import { HomeComponent } from "../home.component";
import Swal from "sweetalert2";


@Component({
  selector: 'app-get-user',
  templateUrl: './get.html',
  styleUrls:['get.css']
})

export class GetTattoComponent implements OnInit {

    users:any;
    editForm: FormGroup
    home:HomeComponent;
    userID:any;
    user:any;
    
    constructor(private activeAouter: ActivatedRoute,private formBuilder: FormBuilder,private router: Router, private apiService: ApiService,private http: HttpClient) { 
     }
  

  
    ngOnInit(){
      
      this.http.get('http://localhost:8080/api/users/' + this.activeAouter.snapshot.params['id']).subscribe(data =>{
        console.log(data);
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
  descricao:['' ,Validators.required]
})
  
  }


  showModal(id){
    if(!window.sessionStorage.getItem('token')) {
      this.router.navigate(['login']);
      Swal.fire('Página inacessivel','Você precisa logar para acessar essa página!','warning');
      return;
    }
    this.userID = id;
 

}

OnSubmit() {

    if(this.editForm.value.descricao === ""){

      Swal.fire("Erro ao adicionar o comentário","O campo do comentário não pode estar vazio","error")

    }else{

    const formData = new FormData();
    formData.append('cli', this.user.id);
    formData.append('tat', this.userID);
    formData.append('coments',JSON.stringify(this.editForm.value))
    const headers = {
      'Authorization': 'Bearer ' + JSON.parse(window.sessionStorage.getItem('token')).access_token
    }
    this.http.post('http://localhost:8080/api/coments/insert',formData,{headers})
      .subscribe(resposta => {
        
        Swal.fire("Comentário adicionado!","","success") 
      
      }, error => {

       
        Swal.fire("Erro ao adicionar o comentário",error.error.message,"error") 

          });

    }
  
    
      
    
  }


      }
  
  