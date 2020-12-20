import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractControl } from '@angular/forms';
import { Config } from 'src/assets/config-properties';
import { EmailCheckDto } from './email-check-dto';

@Injectable({
    providedIn: 'root'
  })
export class EmailValidator {

    constructor(private readonly http: HttpClient) { }

    private checkEmail: EmailCheckDto;

    validate(control: AbstractControl) {
        return new Promise((resolve) => {
            if ((control.dirty && control.value)) {
                this.http.get<EmailCheckDto>(Config.apiUrl + Config.users + `/${control.value}/mail-verify/`)
                    .subscribe(response => {
                        this.checkEmail = response;
                        if (this.checkEmail.isValid) {
                            resolve(null);
                        } else {
                            resolve({ mailTaken: true });
                        }
                    });
            } else {
                resolve(null);
            }
        });
    };
}