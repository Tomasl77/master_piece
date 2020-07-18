import { Injectable } from '@angular/core';
import { baseUrl } from '../../../assets/config-properties';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Token } from 'src/app/shared/token';
import { map, catchError } from 'rxjs/operators';
import { CustomError } from 'src/app/shared/custom-error/custom-error';

@Injectable()
export class LogInService {

  constructor(private readonly http: HttpClient) { }

  private HttpUploadOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/x-www-form-urlencoded" })
  }

  logIn(formData: FormGroup) {
    let datas = new URLSearchParams();
    datas.set("username", formData.value.username);
    datas.set("password", formData.value.password);
    datas.set("client_id", formData.value.client_id);
    datas.set("grant_type", formData.value.grant_type);
    return this.http.post<any>(baseUrl + "/oauth/token", datas.toString(), this.HttpUploadOptions)
      .pipe(map((token: Token) => {
        window.localStorage.setItem('token', JSON.stringify(token));
      }),
      catchError(map((error : CustomError) => {
        console.log(error);
      }))
      );
  }
}
