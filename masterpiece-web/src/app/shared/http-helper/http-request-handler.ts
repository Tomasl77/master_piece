import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable, Subscription } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class HttpRequestHandler {

    constructor(private http: HttpClient) { };

    public post(url:string, inputs:any): Observable<any> {
        return this.http.post(url, inputs);
      }
    
      public get(url: string): Observable<any> {
        return this.http.get(url)
      }
    
      public delete(url: string) : Observable<any> {
        return this.http.delete(url);
      }

    public unsubscribe(subscription: Subscription): void {
        if (subscription) {
            subscription.unsubscribe();
        }
    }
}