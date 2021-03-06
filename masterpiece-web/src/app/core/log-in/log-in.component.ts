import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AuthenticationService } from 'src/app/shared/services/authentication/authentication.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { TranslateService } from '@ngx-translate/core';
import { TokenStorageService } from 'src/app/shared/token-storage.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css'],
  providers: []
})
export class LogInComponent implements OnInit {

  private grant_type: string = "password";
  private client_id: string = "masterpiece-web";
  private isTokenExpired : boolean = true;
  errorReturned: string;

  public logInForm: FormGroup;
  
  constructor(
    private fb: FormBuilder, 
    private authService: AuthenticationService, 
    private router : Router,
    private translateService: TranslateService,
    private tokenStorageService: TokenStorageService
    ) {
    this.logInForm = this.fb.group({
      username: '',
      password: '',
      grant_type: this.grant_type,
      client_id: this.client_id
    });
  }

  ngOnInit() {
    this.isTokenExpired = this.tokenStorageService.isExpired();
    if(this.isTokenExpired) {
      localStorage.removeItem("token")
    } else {
      this.isTokenExpired = false;
      this.router.navigate(['/accounts']);
    }
  }

  logIn() {
    this.authService.logIn(this.logInForm).subscribe(
      () => {
        this.router.navigate(['/accounts']);
      },
      (error: HttpErrorResponse) => {
        if(error.status == 400) {
          this.errorReturned = this.translateService.instant('error.bad-credentials');
          setTimeout(()=> {
            this.errorReturned = null;
          }, 2000)
        }
        if(error.status != 400) {
          this.errorReturned = this.translateService.instant('error.occured');
          setTimeout(()=> {
            this.errorReturned = null;
          }, 2000)
        }
      }
    )
  }
  createAndLogIn(username: string, password : string): void {
    this.logInForm.value.username = username;
    this.logInForm.value.password = password;
    this.logIn();
  }
}
