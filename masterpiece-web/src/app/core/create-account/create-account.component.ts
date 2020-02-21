import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ControlContainer } from '@angular/forms';
import { UsernameValidator } from './username-validator';
import { HttpClient } from '@angular/common/http';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  private serverUrl = "http://localhost:8000/accounts";
  private readonly passwordPatten = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*~{}&.,§+=°_();/]).{8,30}$";

  public signForm: FormGroup;
  constructor(private fb: FormBuilder, usernameValidator: UsernameValidator,
    private readonly http: HttpClient, private translate : TranslateService) {
    this.signForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(20)], usernameValidator.validate.bind(usernameValidator)],
      password: ['', [Validators.required, Validators.pattern((this.passwordPatten))]],
      passwordConfirm: ['', [Validators.required]]
    }, { validators: this.checkPasswords });
  }

validationMessages = {
    'username': {
      'required': 'User name is required',
      'minlength': 'Minimum length : 2 characters',
      'maxlength': 'Maximum length : 20 caracters'
    },
    'password': {
      'required': 'Password is required',
      'pattern': 'Minimum 8 characters, including at least 1 uppercase, and 50 characters maximum.'
    },
    'passwordConfirm': {
      'required': 'Please, confirm password',
      'notSame': 'Confirmation must be indentical to password'
    }
  }

  formErrors = {
    'username': '',
    'password': '',
    'passwordConfirm': ''
  }


  
  logValidationErrors(group: FormGroup = this.signForm): void {
    Object.keys(group.controls).forEach((key: string) => {
      const abstractControl = group.get(key);
      if (abstractControl instanceof FormGroup) {
        this.logValidationErrors(abstractControl)
      } else {
        this.formErrors[key] = '';
        if (abstractControl && !abstractControl.valid && ((abstractControl.touched) || (abstractControl.dirty))) {
          const messages = this.validationMessages[key];
          console.log(messages);
          for (const errorKey in abstractControl.errors) {
            if (errorKey) {
              console.log(errorKey);
              this.formErrors[key] += messages[errorKey] + ' ';
            }
          };
        }
      }
    })
  }

  checkPasswords(group: FormGroup) {
    let pass = group.get('password').value;
    let confirmPass = group.get('passwordConfirm').value;
    if (pass !== confirmPass) {
      group.get('passwordConfirm').setErrors({notSame : true})
    }
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