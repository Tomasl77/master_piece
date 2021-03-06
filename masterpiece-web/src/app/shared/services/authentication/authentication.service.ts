import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { map } from 'rxjs/operators';
import { Token } from '@angular/compiler/src/ml_parser/lexer';
import { Config } from 'src/assets/config-properties';
import { HttpClient } from '@angular/common/http';
import { TokenStorageService } from '../../token-storage.service';
import { User } from '../../models/user.model';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private router: Router, private readonly http: HttpClient, private tokenStorageService: TokenStorageService) {
    this.currentUserSubject = new BehaviorSubject<User>(this.mapUserInfos());
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  logIn(formData: FormGroup): any {
    let datas = new URLSearchParams();
    datas.set("username", formData.value.username);
    datas.set("password", formData.value.password);
    datas.set("client_id", formData.value.client_id);
    datas.set("grant_type", formData.value.grant_type);
    return this.http.post<any>(Config.baseUrl + "/oauth/token", datas.toString(), Config.httpOptions.formUrlEncoded)
      .pipe(map((token: Token) => {
        const mappedToken = this.tokenStorageService.mapToken(token);
        window.localStorage.setItem('token', JSON.stringify(mappedToken));
        const user =  this.mapUserInfos();
        this.currentUserSubject.next(user);
      })
      );
  }

  isAuthenticated(): boolean {
    if (this.tokenStorageService.getToken()) {
      return true;
    }
    return false;
  }


  logout(): void {
    localStorage.removeItem("token");
    this.currentUserSubject.next(null);
    this.router.navigate(['/login'])
  }

  mapUserInfos(): User {
    const token = this.tokenStorageService.getToken();
    if(token != null) {
      const jwtDecoded = this.tokenStorageService.parseJwt(token.accessToken);
      const user = new User(jwtDecoded.user_name, jwtDecoded.userId, jwtDecoded.authorities); 
      return user;
    }
  }
}
