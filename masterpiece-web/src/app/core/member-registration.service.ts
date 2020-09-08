import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Observable, throwError } from 'rxjs';
import { AccountDto } from './account/accountDto';
import { Config } from 'src/assets/config-properties';


@Injectable()
export class MemberRegistrationService {

  constructor(private readonly http: HttpClient) { }

  createAccount(formData: FormGroup): Observable<AccountDto> {
    return this.http.post<any>(Config.apiUrl + Config.users, formData.value);
  }

  getAccount(id: number): Observable<AccountDto> {
    return this.http.get<AccountDto>(`${Config.apiUrl + Config.users}/${id}`)
  }
}
