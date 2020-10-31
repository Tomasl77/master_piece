import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../../token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor {

  constructor(private tokenStorageService : TokenStorageService) { }
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.tokenStorageService.getToken();
    if(token) {
      req = req.clone({
        setHeaders : {
          Authorization: "Bearer " + token.accessToken
        }
      })
    }
    return next.handle(req);
  }
}
