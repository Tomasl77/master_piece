import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Config } from 'src/assets/config-properties';
import { TokenStorageService } from 'src/app/shared/token-storage.service';
import { Subject } from './subject.model';
import { config } from 'process';
import { Observable, Subscription } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpRequestHandler } from 'src/app/shared/http-helper/http-request-handler';

@Injectable()
export class SubjectService {
    

  constructor(private readonly http: HttpRequestHandler, public tokenService : TokenStorageService) { }

  postSubject(form : FormGroup) {
    return this.http.post(Config.apiUrl + Config.subjects, form.value)
  }

  deleteSubject(id: number) {
    return this.http.delete(Config.apiUrl + Config.subjects+ Config.actions.delete + `/${id}`);
  }

  getAllSubject(): Observable<Subject[]> {
    return this.http.get(Config.apiUrl+Config.subjects).pipe(map(response => response.map(
      (subject: Subject) => ({
        id: subject.id,
        title : subject.title,
        description : subject.description,
        category : subject.category,
        member : subject.member
      })
    )));
  }

  unsubscribe(subscription: Subscription) {
    this.http.unsubscribe(subscription);
  }
}
