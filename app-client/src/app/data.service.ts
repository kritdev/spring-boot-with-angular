import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs'; 
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private httpClient: HttpClient) { }

  retrieveData() {
    return this.httpClient
      .get(`http://localhost:8080/api/message`)
      .pipe( 
        catchError(error => {  return throwError('retrieveData Error.'); }) 
      )
  }
}
