import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable, Subscription, throwError } from 'rxjs';
import { AccountDto } from './account/accountDto';
import { Config } from 'src/assets/config-properties';
import { CustomUser } from '../shared/models/custom-user.model';
import { HttpRequestHandler } from '../shared/http-helper/http-request-handler';


@Injectable()
export class UserRegistrationService {

  constructor(private readonly http: HttpRequestHandler) { }

  createAccount(formData: FormGroup): Observable<AccountDto> {
    return this.http.post(Config.apiUrl + Config.users, formData.value);
  }

  getAccount(id: number): Observable<CustomUser> {
    return this.http.get(`${Config.apiUrl + Config.users}/${id}`)
  }

  getAllUsers(): Observable<CustomUser[]> {
    return this.http.get(Config.apiUrl + Config.users)
  }

  unsubscribe(subscription: Subscription) {
    this.http.unsubscribe(subscription);
  }
}
