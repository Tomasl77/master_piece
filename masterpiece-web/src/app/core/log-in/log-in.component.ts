import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { UsernameValidator } from '../create-account/username-validator';
import { HttpClient } from '@angular/common/http';
import { TranslateService } from '@ngx-translate/core';
import { Observable } from 'rxjs';
import { Token } from "../../shared/models/token.model"
import { AuthenticationService } from 'src/app/shared/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css'],
  providers: []
})
export class LogInComponent implements OnInit {

  private grant_type: string = "password";
  private client_id: string = "masterpiece-web"

  public logInForm: FormGroup;
  private readonly passwordPatten = "^(?=.*?[A-Z])(?=.*?[a-z]).{8,}$";

  constructor(private fb: FormBuilder, usernameValidator: UsernameValidator,
    private readonly http: HttpClient, translate: TranslateService, private authService: AuthenticationService, private router : Router) {
    this.logInForm = this.fb.group({
      username: '',
      password: '',
      grant_type: this.grant_type,
      client_id: this.client_id
    });
  }

  ngOnInit() {
  }

  logIn() {
    this.authService.logIn(this.logInForm).subscribe(
      () => {
        this.router.navigate(['/accounts']),
        console.log(" LoggedIn successful")
      },
      (error) => {
        console.log(error.error);
      })
  }
  createAndLogIn(username: string, password : string): void {
    this.logInForm.value.username = username;
    this.logInForm.value.password = password;
    this.logIn();
  }
}
