import { DatePipe } from '@angular/common';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { SharingSession } from 'src/app/shared/models/sharing-session.model';
import { HttpRequestHandler } from 'src/app/shared/services/http-helper/http-request-handler';
import { TokenStorageService } from 'src/app/shared/token-storage.service';
import { Config } from 'src/assets/config-properties';

@Injectable({
  providedIn: 'root'
})
export class SharingSessionService {

  private readonly baseUrl = Config.apiUrl + Config.sharingsession;

  constructor(private readonly http: HttpRequestHandler,
    private datePipe : DatePipe, 
    public tokenService : TokenStorageService) { 
    }

    getAll() : Observable<SharingSession[]> {
      return this.http.get(this.baseUrl).pipe(map(response => response.map(
        (session) => ({
          id: session.id,
          startTime : this.convertDate(session.startTime),
          endTime : this.convertDate(session.endTime),
          subject : session.subject,
          lecturer : session.userProfile,
        })
      )));;
    }

    private convertDate(date : Date)  {
      return this.datePipe.transform(date, 'yyyy-MM-dd HH:mm')
    }
}