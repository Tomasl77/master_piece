import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Config } from 'src/assets/config-properties';
import { TokenStorageService } from 'src/app/shared/token-storage.service';
import { Subject } from './subject.model';
import { config } from 'process';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable()
export class SubjectService {  

  constructor(private readonly http: HttpClient, public tokenService : TokenStorageService) { }

  postSubject(form : FormGroup) {
    return this.http.post<any>(Config.apiUrl + Config.subjects, form.value)
  }

  deleteSubject(id: number) {
    return this.http.delete<any>(Config.apiUrl + Config.subjects+ Config.actions.delete + `/${id}`);
  }

  getAllSubject(): Observable<Subject[]> {
    return this.http.get<Subject[]>(Config.apiUrl+Config.subjects).pipe(map(response => response.map(
      (subject): Subject => ({
        id: subject.id,
        title : subject.title,
        description : subject.description,
        category : subject.category,
        memberId : subject.memberId,
        memberUserUsername : subject.username
      })
    )));
  }
}
