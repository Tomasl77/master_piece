import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AbstractControl} from '@angular/forms';
import {Injectable} from '@angular/core';
import { Config } from 'src/assets/config-properties';
            
@Injectable({
  providedIn: 'root'
})
export class UsernameValidator {

  private timeout;

  constructor(private readonly http: HttpClient) {
  }

  
    validate(control: AbstractControl) {
      return new Promise((resolve) => {        
          if ((control.dirty)) {
            this.http.get<object>(Config.apiUrl+`/users/${control.value}/verify/`)
              .subscribe(res => {
                if (res) {
                  resolve(console.log(res));
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
 

