import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { map } from 'rxjs/operators';
import { Token } from '@angular/compiler/src/ml_parser/lexer';
import { Config } from 'src/assets/config-properties';
import { HttpClient } from '@angular/common/http';
import { TokenStorageService } from '../token-storage.service';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private loggedIn = new BehaviorSubject<boolean>(false);
  constructor(private router : Router, private readonly http: HttpClient, private tokenStorageService : TokenStorageService) { }


  logIn(formData: FormGroup) : any {
    let datas = new URLSearchParams();
    datas.set("username", formData.value.username);
    datas.set("password", formData.value.password);
    datas.set("client_id", formData.value.client_id);
    datas.set("grant_type", formData.value.grant_type);
    return this.http.post<any>(Config.baseUrl + "/oauth/token", datas.toString(), Config.httpOptions.formUrlEncoded)
      .pipe(map((token: Token) => {
        const mappedToken = this.tokenStorageService.mapToken(token);
        window.localStorage.setItem('token', JSON.stringify(mappedToken));
      })
      );
  }

  isAuthenticated(): boolean {
    if(this.tokenStorageService.getToken()){
      return true;
    }
    return false;
  }
  
 
  logout(): void {
    localStorage.removeItem("token");
    this.router.navigate(['/login'])
  }
}
