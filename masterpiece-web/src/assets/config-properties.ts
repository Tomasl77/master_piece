import { HttpHeaders } from '@angular/common/http';

const baseUrl = "http://localhost:8000";

const passwordPattern = '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*~{}&.,§+=°_();/]).{8,30}$';

const emailPattern = '^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$';

export const Config = {
    passwordPattern: passwordPattern,
    emailPattern: emailPattern,
    baseUrl : baseUrl,
    apiUrl: baseUrl + "/api",
    users: "/users",
    subjects: "/subjects",
    sharingsession: "/sharing-sessions",
    category:"/categories",
    httpOptions: {
        json: { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) },
        formUrlEncoded: { headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' }) 
      }
    }
}