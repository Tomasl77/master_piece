import { HttpHeaders } from '@angular/common/http';

const baseUrl = "http://localhost:8000";

const passwordPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*~{}&.,§+=°_();/]).{8,30}$";

export const Config = {
    passwordPattern: passwordPattern,
    baseUrl : baseUrl,
    apiUrl: baseUrl + "/api",
    users: "/users",
    subjects: "/subjects",
    sharingsession: "/sharing-sessions",
    httpOptions: {
        json: { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) },
        formUrlEncoded: { headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' }) 
      }
    }
}