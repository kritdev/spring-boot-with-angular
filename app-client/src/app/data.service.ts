import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs'; 
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  //consume environment setting
  apiUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  retrieveData() {
    return this.httpClient
      .get(`${this.apiUrl}api/message`)
      .pipe( 
        catchError(error => {  return throwError('retrieveData Error.'); }) 
      )
  }
}
