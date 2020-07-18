import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Observable, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { CustomError } from '../shared/custom-error/custom-error';
import { CustomUser } from './custom-user';
import { CustomUserDto } from './custom-user/CustomUserDto';

@Injectable()
export class CustomUserRegistrationService {

  private baseUrl = "http://localhost:8000/users";

  constructor(private readonly http: HttpClient) { }

  createAccount(formData: FormGroup): Observable<CustomUserDto> {
    return this.http.post<any>(this.baseUrl, formData.value).pipe(tap(()=> {}),
      catchError(this.handleError))
    }

  getAccount(id: number): Observable<CustomUser> {
    return this.http.get<CustomUser>(`${this.baseUrl}/${id}`)
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
