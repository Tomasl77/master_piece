import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';

@Injectable()
export class AccountRegistrationService {

  private baseUrl = "http://localhost:8000/accounts";

  constructor(private readonly http: HttpClient) { }

  createAccount(formData : FormGroup): Observable<any> {
    return this.http.post<object>(this.baseUrl, formData.value)
  }
  getAccount(id: number): Observable<any> {
    console.log("id = " + id);
    return this.http.get(`${this.baseUrl}/${id}`);
  }
}
