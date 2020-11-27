import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Config } from 'src/assets/config-properties';
import { Category } from '../models/category.model';
import { TokenStorageService } from '../token-storage.service';
import { HttpRequestHandler } from './http-helper/http-request-handler';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private readonly baseUrl = Config.apiUrl + Config.category;

  constructor(private readonly http: HttpRequestHandler) { }

  getAllCategories() : Observable<Category[]> {
    return this.http.get(this.baseUrl);
  }
}
