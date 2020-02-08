import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AbstractControl} from '@angular/forms';
import {Injectable} from '@angular/core';

const headers = new HttpHeaders()
            .set("Access-Control-Allow-Origin", "*");
            
@Injectable({
  providedIn: 'root'
})
export class UsernameValidator {

  private timeout;

  constructor(private readonly http: HttpClient) {
  }

  
    validate(control: AbstractControl) {
      clearTimeout(this.timeout);
      return new Promise((resolve) => {
        this.timeout = setTimeout(() => {
          if (control.value.length >= 2) {
            this.http.get<object>(`http://localhost:8000/accounts/${control.value}/verify/`, {headers})
              .subscribe(res => {
                if (res) {
                  resolve(null);
                } else {
                  resolve({nameTaken: true});
                }
              });
          } else {
            resolve(null);
          }
        }, 1000);
      });
  }
 
};
