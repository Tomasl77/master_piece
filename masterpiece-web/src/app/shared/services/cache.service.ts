import { Injectable } from '@angular/core';
import { Config } from 'src/assets/config-properties';
import { HttpRequestHandler } from './http-helper/http-request-handler';

@Injectable({
  providedIn: 'root'
})
export class CacheService {

  private readonly baseUrl = Config.apiUrl + Config.cache;

  constructor(private readonly http: HttpRequestHandler) { }

  cleanCache(cacheName: string) {
    console.log("into cache service")
    this.http.get(this.baseUrl + "/" + cacheName);
    console.log(this.baseUrl + "/" + cacheName);
  }
}
