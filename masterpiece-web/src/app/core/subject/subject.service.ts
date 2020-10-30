import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Config } from 'src/assets/config-properties';
import { TokenStorageService } from 'src/app/shared/token-storage.service';
import { Subject } from '../../shared/models/subject.model';
import { Observable, Subscription } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpRequestHandler } from 'src/app/shared/http-helper/http-request-handler';
import { config } from 'process';

@Injectable()
export class SubjectService {
    
  private readonly baseUrl = Config.apiUrl + Config.subjects;

  constructor(private readonly http: HttpRequestHandler, public tokenService : TokenStorageService) { }

  postSubject(form : FormGroup) {
    return this.http.post(this.baseUrl, form.value)
  }

  deleteSubject(id: number) {
    return this.http.delete(this.baseUrl + `/${id}`);
  }

  getAllSubject(): Observable<Subject[]> {
    return this.http.get(this.baseUrl).pipe(map(response => response.map(
      (subject: Subject) => ({
        id: subject.id,
        title : subject.title,
        description : subject.description,
        category : subject.category,
        user : subject.user.credentials.username,
        vote : subject.vote,
        credentials: subject.user.credentials
      })
    )));
  }


  presentSubject(form : FormGroup) {
    return this.http.post(Config.apiUrl + "/sharing-sessions", form.value);
  }

  unsubscribe(subscription: Subscription) {
    this.http.unsubscribe(subscription);
  }
}
