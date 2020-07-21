import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class SubjectService {

  constructor(private readonly http: HttpClient) { }

  private HttpUploadOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/json" })
  }
}
