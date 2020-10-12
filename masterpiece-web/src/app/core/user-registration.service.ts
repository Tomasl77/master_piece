import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable, Subscription, throwError } from 'rxjs';
import { AccountDto } from '../shared/models/accountDto';
import { Config } from 'src/assets/config-properties';
import { UserProfile } from '../shared/models/user-profile.model';
import { HttpRequestHandler } from '../shared/http-helper/http-request-handler';


@Injectable()
export class UserRegistrationService {
  
  constructor(private readonly http: HttpRequestHandler) { }

  private readonly baseUrl = Config.apiUrl + Config.users;

  createAccount(formData: FormGroup): Observable<AccountDto> {
    return this.http.post(this.baseUrl, formData.value);
  }

  getAccount(id: number): Observable<UserProfile> {
    return this.http.get(`${this.baseUrl}/${id}`)
  }

  getAllUsers(): Observable<UserProfile[]> {
    return this.http.get(this.baseUrl)
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(this.baseUrl+ Config.actions.delete+ `/${id}`)
  }

  updateUser(formData :FormGroup) {
    return this.http.update(this.baseUrl + Config.actions.update, formData.value)
  }

  unsubscribe(subscription: Subscription) {
    this.http.unsubscribe(subscription);
  }
}
