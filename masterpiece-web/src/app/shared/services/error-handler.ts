import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ErrorHandler {
  static catch(error: any) {
    let message = "";
    message += error.error.status + " : ";
    const errors = error.error.errors;
    errors.forEach((err: any) => message += err +  ", " );
    message = message.slice(0, -2);
    return message;
  }
}