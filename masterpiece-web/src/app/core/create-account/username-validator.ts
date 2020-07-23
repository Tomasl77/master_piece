import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AbstractControl} from '@angular/forms';
import {Injectable} from '@angular/core';
            
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
            this.http.get<object>(`http://localhost:8000/users/${control.value}/verify/`)
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
        }, 1000);
      });
  }
 
};
