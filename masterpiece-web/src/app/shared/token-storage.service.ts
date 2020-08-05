import { Injectable } from '@angular/core';
import { Token } from './token';

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
}
