import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { UsernameValidator } from '../create-account/username-validator';
import { HttpClient } from '@angular/common/http';
import { TranslateService } from '@ngx-translate/core';
import { Observable } from 'rxjs';
import { mapToMapExpression } from '@angular/compiler/src/render3/util';
import { Token } from "../../shared/token"
import { AuthenticationService } from 'src/app/shared/services/authentication.service';

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
    private readonly http: HttpClient, translate: TranslateService, private authService: AuthenticationService) {
    this.logInForm = this.fb.group({
      username: '',
      password: '',
      grant_type: this.grant_type,
      client_id: this.client_id
    });
  }

  private token: Token;

  ngOnInit() {
  }

  logIn() {
    this.authService.logIn(this.logInForm).subscribe(
      (data) => {
        console.log(" LoggedIn successful")
      },
      (error) => {
        console.log(error.error);
      })
  }

}
