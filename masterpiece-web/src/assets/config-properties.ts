import { HttpHeaders } from '@angular/common/http';

const baseUrl = "http://localhost:8000";

export const Config = {
    baseUrl : baseUrl,
    apiUrl: baseUrl + "/api",
    users: "/users",
    subjects: "/subjects",
    httpOptions: {
        json: { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) },
        formUrlEncoded: { headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' }) 
      }
    }
}