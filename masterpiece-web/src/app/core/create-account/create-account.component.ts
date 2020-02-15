import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UsernameValidator } from './username-validator';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  private serverUrl = "http://localhost:8000/accounts";
  private readonly passwordPatten = "^(?=.*?[A-Z])(?=.*?[a-z]).{8,}$";

  public signForm: FormGroup;
  constructor(private fb: FormBuilder, usernameValidator: UsernameValidator,
    private readonly http: HttpClient) {
    this.signForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(2)], usernameValidator.validate.bind(usernameValidator)],
      password: ['', [Validators.required, Validators.pattern((this.passwordPatten))]]
    });
  }

validationMessages = {
  'username' : {
    'required' : 'User name is required',
    'minlength' : 'Minimum length : 2 characters'
  },
  'password' : {
    'required' : 'Password is required',
    'pattern' : 'Minimum 8 characters, including at least 1 uppercase.'
  } 
}

formErrors = {
  'username' : '',
  'minLength' : ''
}

logValidationErrors(group : FormGroup = this.signForm) : void {
  Object.keys(group.controls).forEach((key : string) => {
    const abstractControl = group.get(key);
    if (abstractControl instanceof FormGroup) {
      this.logValidationErrors(abstractControl) 
    } else {
      this.formErrors[key]='';
      if (abstractControl && !abstractControl.valid && ((abstractControl.touched) || (abstractControl.dirty))) {
        const messages = this.validationMessages[key];
        for(const errorKey in abstractControl.errors) {
          if(errorKey) {
            this.formErrors[key] += messages[errorKey] + ' ';
          }
        };
      }
    }
  })
}

  ngOnInit() {
    this.signForm.valueChanges.subscribe((data) => {
      this.logValidationErrors(this.signForm);
    });
  }
  register() {
    console.log("submit : " + this.signForm.value.username);
    this.http.post<object>(this.serverUrl, this.signForm.value)
      .subscribe();
    //TODO: Learn to get errors !
    this.signForm.reset();
  }
}