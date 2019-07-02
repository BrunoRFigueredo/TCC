import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'bwm-image-upload',
  templateUrl: 'image.component.html',
  styleUrls:['image.component.css']
})

export class ImageUploadComponent {
  
  constructor(private http: HttpClient) {}

inputFileChange(event) {
  if (event.target.files && event.target.files[0]) {
    const foto = event.target.files[0];

    const formData = new FormData();
    formData.append('file', foto);

    this.http.post('http://localhost:8080/api/imagens/uploadFile', formData)
      .subscribe(resposta => console.log('Upload ok.'));
  }
}

}



