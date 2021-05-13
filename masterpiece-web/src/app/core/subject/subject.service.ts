import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Config } from 'src/assets/config-properties';
import { Subject } from '../../shared/models/subject.model';
import { Observable, Subscription } from 'rxjs';
import { HttpRequestHandler } from 'src/app/shared/services/http-helper/http-request-handler';
import { SubjectViewDtoWithVote } from 'src/app/shared/models/subject-with-vote.model';
import { VotedSubjectByUser } from 'src/app/shared/models/voted-subject-by-user.model';

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

  getAllSubjects(): Observable<SubjectViewDtoWithVote[]> {
    return this.http.get(this.baseUrl);
  }

  getAllVotedSubjectForUser(): Observable<VotedSubjectByUser[]> {
    return this.http.get(this.baseUrl + `/byUser`)
  }

  voteForSubject(id:number, form: FormGroup): Observable<SubjectViewDtoWithVote> {
    return this.http.post(this.baseUrl + `/vote/${id}`, form.value);
  }

  presentSubject(form : FormGroup) {
    return this.http.post(Config.apiUrl + "/sharing-sessions", form.value);
  }

  unsubscribe(subscription: Subscription) {
    this.http.unsubscribe(subscription);
  }
}
