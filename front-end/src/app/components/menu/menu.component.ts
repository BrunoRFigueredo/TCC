import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'app/services/login/service';
import { Observable } from 'rxjs';
import { delay, share } from 'rxjs/operators';


@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
  })
  
  export class MenuComponent{

    user:any;

    constructor(private router: Router,private apiService: ApiService) {

    

    }


    ngOnInit(){
      this.apiService.current()
      .subscribe( data => {
          this.user = data; 
          console.log(data)
  }, error => {
    console.log(error);
  });
    }

    deslogar() {
      this.router.navigate(['/home'])
      .then(() => {
        window.location.reload();
      });
    window.location.reload();
      window.sessionStorage.removeItem('token');
      
    }


    
  }