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
  apiUrl = environment.apiUrl + 'api/message';

  constructor(private httpClient: HttpClient) { }

  public retrieveData() {
    return this.httpClient
      .get(`${this.apiUrl}`)
      .pipe( 
        catchError(error => {  return throwError('RetrieveData fail.'); }) 
      );
  }

  public find(id: number) {
    return this.httpClient
      .get(`${this.apiUrl}/${id}`)
      .pipe( 
        catchError(error => {  return throwError('Find fail.'); }) 
      );
  }

  public delete(id: number) {
    return this.httpClient
      .delete(`${this.apiUrl}/${id}`)
      .pipe( 
        catchError(error => {  return throwError('Delete fail.'); }) 
      );
  }

  public create(data) {
    return this.httpClient
      .post(`${this.apiUrl}`, data)
      .pipe( 
        catchError(error => {  return throwError('Create fail.'); }) 
      );
  }

  public update(data) {
    return this.httpClient
      .put(`${this.apiUrl}`, data)
      .pipe( 
        catchError(error => {  return throwError('Update fail.'); }) 
      );
  }
}
