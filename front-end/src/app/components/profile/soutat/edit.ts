import { Validators, FormGroup, FormBuilder, FormControl } from "@angular/forms";
import { OnInit, Component } from "@angular/core";
import { User } from "app/services/login/user.model";
import { Router } from "@angular/router";
import { ApiService } from "app/services/login/service";
import { HttpClient } from "@angular/common/http";
import Swal from "sweetalert2";


@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit.html',
  styleUrls:['edit.css']
})

export class TatUserComponent implements OnInit {

    user: User;
    users:any;
    editForm: FormGroup;
    img:any;
    constructor(private formBuilder: FormBuilder,private router: Router, private apiService: ApiService,private http: HttpClient) { }
  
    ngOnInit() {
        if(!window.sessionStorage.getItem('token')) {
          this.router.navigate(['login']);
          return;
        }
        
        this.apiService.current()
          .subscribe( data => {
            console.log(data)
              this.users = data;
            this.editForm.setValue(this.users);
        });
              this.editForm = this.formBuilder.group({
                id:'',
                name:['', Validators.required],
                email:['', Validators.required],
                password:['',Validators.required],
                matchPassword:['',Validators.required],
                cliente:this.formBuilder.group({
                  id:'',
                  cpf:['', Validators.required],
                  telefone:['',Validators.required],
                  endereco:this.formBuilder.group({
                    id:'',
                    bairro:['', Validators.required],
                    cidade:['', Validators.required],
                    unidadeFederativa:['', Validators.required]
                  })
                }),
                tatuador:this.formBuilder.group({
                    id:'',
                    nomeSocial:['', Validators.required],
                    nomeEstudio:['', Validators.required],
                    descricao:['', Validators.required],
                    imagem:[],
                    endereco:this.formBuilder.group({
                      id:'',
                      bairro:['', Validators.required],
                      cidade:['', Validators.required],
                      unidadeFederativa:['', Validators.required]
                    })
                }),
                imagem:this.formBuilder.group({
                  id:'',
                  data:['', Validators.required],
                  fileName:['',Validators.required],
                  fileType:['',Validators.required],
                })
              });
    }
  
    onSubmit() {
      
       this.http.put('http://localhost:8080/api/users/' + this.users.id + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, this.editForm.value)
        .pipe()
        .subscribe(
          data => {
              Swal.fire("Por favor,relogue para aplicar as alterações!","O seu perfil será avaliado para validação do seu estúdio,agradecemos pela compreensão!","success");
              this.router.navigate(['/login']);
          },
          error => {
            alert(error);
          });
    }
  
  }