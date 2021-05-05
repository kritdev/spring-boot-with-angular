import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs'; 
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  apiUrl = environment.apiUrl + 'api/report';

  constructor(private httpClient: HttpClient) { }

  public retrieveReport() {
    const httpOptions = {
      'responseType'  : 'arraybuffer' as 'json'
    };

    return this.httpClient
      .get(`${this.apiUrl}/pdf`, httpOptions)
      .pipe( 
        catchError(error => {  return throwError('retrieveReport fail.'); }) 
      );
  }
}
