import { Injectable } from '@angular/core';
import { Token } from './models/token.model';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  getToken() : Token {
    return JSON.parse(localStorage.getItem('token'));
  }

  mapToken(token) : Token {
    return new Token(token.access_token, token.token_type,
      token.refresh_token, token.expires_in, token.scope, token.jti, token.userId)
  }

  parseJwt(token: string) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(atob(base64));
  };

  isExpired(): boolean {
    const token = this.getToken();
    if(token != null) {
      const jwtDecoded = this.parseJwt(token.accessToken); 
      const expirationDate = jwtDecoded.exp;
      return (Math.floor((new Date).getTime() / 1000)) >= expirationDate;
    }
  }
}
