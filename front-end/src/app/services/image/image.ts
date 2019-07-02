import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class ImageService {

  

  constructor(private httpClient: HttpClient) { }

  getImage(imageUrl: string): Observable<Blob> {
    return this.httpClient.get(imageUrl, { responseType: 'blob' });
  }

}