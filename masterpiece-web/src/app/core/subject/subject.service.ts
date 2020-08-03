import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Config } from 'src/assets/config-properties';
import { TokenStorageService } from 'src/app/shared/token-storage.service';
import { tokenName } from '@angular/compiler';
import { Token } from 'src/app/shared/token';

@Injectable()
export class SubjectService {

  constructor(private readonly http: HttpClient, public tokenService : TokenStorageService) { }

  private token : Token = this.tokenService.getToken();

  private HttpUploadOptions = {
    headers: new HttpHeaders({
      "Authorization": "Bearer " + this.token.accessToken})
  }

  postSubject(form : FormGroup) {
    return this.http.post<any>(Config.apiUrl + Config.subjects, form.value, this.HttpUploadOptions)
  }
}
