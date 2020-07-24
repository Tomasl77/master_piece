import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ControlContainer } from '@angular/forms';
import { UsernameValidator } from './username-validator';
import { HttpClient } from '@angular/common/http';
import { TranslateService } from '@ngx-translate/core';
import { catchError } from 'rxjs/operators';
import { CustomUserRegistrationService } from '../custom-user-registration.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css'],
  providers: [CustomUserRegistrationService]
})
export class CreateAccountComponent implements OnInit {

  private readonly passwordPatten = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*~{}&.,§+=°_();/]).{8,30}$";

  public signForm: FormGroup;
  constructor(private fb: FormBuilder, usernameValidator: UsernameValidator,
    private readonly http: HttpClient, private translate: TranslateService, private accountService : CustomUserRegistrationService) {
    this.signForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(20)], usernameValidator.validate.bind(usernameValidator)],
      password: ['', [Validators.required, Validators.pattern((this.passwordPatten))]],
      passwordConfirm: ['', [Validators.required]]
    }, { validators: this.checkPasswords });
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
          for (const errorKey in abstractControl.errors) {
            if (errorKey) {
              const err = this.translate.instant('create-account.validationMessages.' + key +'.'+ errorKey);
              this.formErrors[key] += err;
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
      group.get('passwordConfirm').setErrors({ notSame: true })
    }
  }

  ngOnInit() {
    this.signForm.valueChanges.subscribe((data) => {
      this.logValidationErrors(this.signForm);
    });
  }

  register() {
    console.log(this.signForm.valueChanges);
    this.accountService.createAccount(this.signForm).subscribe(
      (data) => {
        console.log("Account created with success : " + data.username)
        this.signForm.reset()
      },
      ((error) => { 
        console.log(error)
      })
  )};
}