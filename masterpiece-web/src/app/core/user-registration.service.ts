import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable, Subscription, throwError } from 'rxjs';
import { AccountDto } from '../shared/models/accountDto';
import { Config } from 'src/assets/config-properties';
import { HttpRequestHandler } from '../shared/services/http-helper/http-request-handler';
import { UserCredentials } from '../shared/models/user-credentials.model';


@Injectable()
export class UserRegistrationService {
  
  constructor(private readonly http: HttpRequestHandler) { }

  private readonly baseUrl = Config.apiUrl + Config.users;

  createAccount(formData: FormGroup): Observable<AccountDto> {
    return this.http.post(this.baseUrl, formData.value);
  }

  getAccount(id: number): Observable<UserCredentials> {
    return this.http.get(`${this.baseUrl}/${id}`)
  }

  getAllUsers(): Observable<UserCredentials[]> {
    return this.http.get(this.baseUrl)
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(this.baseUrl+ `/${id}`)
  }

  updateUser(formData :FormGroup) {
    return this.http.update(this.baseUrl, formData.value)
  }

  unsubscribe(subscription: Subscription) {
    this.http.unsubscribe(subscription);
  }
}
