import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Config } from 'src/assets/config-properties';
import { TokenStorageService } from 'src/app/shared/token-storage.service';
import { Token } from 'src/app/shared/models/token';

@Injectable()
export class SubjectService {
  

  constructor(private readonly http: HttpClient, public tokenService : TokenStorageService) { }

  postSubject(form : FormGroup) {
    return this.http.post<any>(Config.apiUrl + Config.subjects, form.value)
  }

  deleteSubject(id: number) {
    return this.http.delete<any>(Config.apiUrl + Config.subjects+ Config.actions.delete + `/${id}`);
  }
}
