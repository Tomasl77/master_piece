import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {UsernameValidator} from '../create-account/username-validator';
import {HttpClient} from '@angular/common/http';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  public logInForm: FormGroup;
  private readonly passwordPatten = "^(?=.*?[A-Z])(?=.*?[a-z]).{8,}$";

  constructor(private fb: FormBuilder, usernameValidator: UsernameValidator,
    private readonly http: HttpClient, translate: TranslateService) {
    this.logInForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(2)], usernameValidator.validate.bind(usernameValidator)],
      password: ['', [Validators.required, Validators.pattern((this.passwordPatten))]]
    });
  }

  ngOnInit() {
  }

  logIn() {
    
  }

}
