import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Config } from 'src/assets/config-properties';
import { Subject } from '../../shared/models/subject.model';
import { Observable, Subscription } from 'rxjs';
import { HttpRequestHandler } from 'src/app/shared/services/http-helper/http-request-handler';
import { SubjectWithVote } from 'src/app/shared/models/subject-with-vote.model';

@Injectable()
export class SubjectService {
    
  private readonly baseUrl = Config.apiUrl + Config.subjects;

  constructor(private readonly http: HttpRequestHandler) { }

  postSubject(form : FormGroup): Observable<Subject> {
    return this.http.post(this.baseUrl, form.value)
  }

  deleteSubject(id: number) {
    return this.http.delete(this.baseUrl + `/${id}`);
  }

  getAllSubject(): Observable<SubjectWithVote[]> {
    return this.http.get(this.baseUrl);
  }


  presentSubject(form : FormGroup) {
    return this.http.post(Config.apiUrl + "/sharing-sessions", form.value);
  }

  unsubscribe(subscription: Subscription) {
    this.http.unsubscribe(subscription);
  }
}
