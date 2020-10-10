import { HttpHeaders } from '@angular/common/http';

const baseUrl = "http://localhost:8000";

const passwordPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*~{}&.,§+=°_();/]).{8,30}$";

export const Config = {
    passwordPattern: passwordPattern,
    baseUrl : baseUrl,
    apiUrl: baseUrl + "/api",
    users: "/users",
    subjects: "/subjects",
    actions: {
      get : "/get",
      create : "/create",
      delete : "/delete",
      update : "/update"
    },
    httpOptions: {
        json: { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) },
        formUrlEncoded: { headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' }) 
      }
    }
}