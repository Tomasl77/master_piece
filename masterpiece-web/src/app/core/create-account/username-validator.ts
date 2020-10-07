import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AbstractControl } from '@angular/forms';
import { Injectable } from '@angular/core';
import { Config } from 'src/assets/config-properties';
import { UsernameCheckDto } from './username-check.model';

@Injectable({
  providedIn: 'root'
})
export class UsernameValidator {

  constructor(private readonly http: HttpClient) {
  }

  private checkUsername : UsernameCheckDto;

  validate(control: AbstractControl) {
    return new Promise((resolve) => {
      if ((control.touched && control.value)) {
        this.http.get<UsernameCheckDto>(Config.apiUrl + Config.users + `/${control.value}/verify/`)
          .subscribe(response => {
            this.checkUsername = response;
            if (this.checkUsername.isValid) {
              resolve();
            } else {
              resolve({nameTaken: true});
            }
          });
      } else {
        resolve(null);
      }
    });
  };
}