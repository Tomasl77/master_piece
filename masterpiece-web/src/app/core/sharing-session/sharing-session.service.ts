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
        (session: SharingSession) => ({
          id: session.id,
          day: this.convertDate(session.startTime),
          startTime : this.convertTime(session.startTime),
          endTime : this.convertTime(session.endTime),
          subject : session.subject,
          lecturer : session.lecturer,
        })
      )));;
    }

    private convertDate(date : Date)  {
      return this.datePipe.transform(date, 'yyyy-MM-dd')
    }

    private convertTime(date : Date)  {
      return this.datePipe.transform(date, 'HH:mm')
    }
}
