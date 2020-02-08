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

  validate(control: AbstractControl): Promise<{ [key: string]: boolean }> {
    clearTimeout(this.timeout);

    const value = control.value;

    return new Promise((resolve, reject) => {
      this.timeout = setTimeout(() => {
        this.http.get<object>(`http://localhost:8000/accounts/${control.value}/verify/`, {headers})
          .subscribe(flag => {
              if (flag) {
                resolve({'usernameTaken': true});
              } else {
                resolve(null);
              }
            },
            (err) => {
              console.log(err);
            }
          );
      }, 200);
    });
  }

}

