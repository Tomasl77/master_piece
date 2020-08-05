import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Observable, throwError } from 'rxjs';
import { CustomUserDto } from './custom-user/CustomUserDto';
import { Config } from 'src/assets/config-properties';


@Injectable()
export class CustomUserRegistrationService {

  constructor(private readonly http: HttpClient) { }

  createAccount(formData: FormGroup): Observable<CustomUserDto> {
    return this.http.post<any>(Config.apiUrl + Config.users, formData.value);
  }

  getAccount(id: number): Observable<CustomUserDto> {
    return this.http.get<CustomUserDto>(`${Config.apiUrl + Config.users}/${id}`)
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${JSON.stringify(error)}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };

}
