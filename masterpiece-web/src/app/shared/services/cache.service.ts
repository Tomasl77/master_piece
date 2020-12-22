import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Config } from 'src/assets/config-properties';
import { HttpRequestHandler } from './http-helper/http-request-handler';

@Injectable({
  providedIn: 'root'
})
export class CacheService {

  private readonly baseUrl = Config.apiUrl + Config.cache;

  constructor(private readonly http: HttpRequestHandler) { }

  cleanCache(cacheName: string): Observable<any> {
    return this.http.get(this.baseUrl + "/" + cacheName);
  }
}
