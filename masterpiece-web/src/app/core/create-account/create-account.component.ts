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

  private validationMessage : string = "create-account.validationMessages";

  public signForm: FormGroup;

  private formToReturn : FormGroup;

  constructor(private fb: FormBuilder, usernameValidator: UsernameValidator,
    private translate: TranslateService, private accountService : CustomUserRegistrationService) {
    this.signForm = this.fb.group({
      email:['', [Validators.required, Validators.email, Validators.maxLength(255)]],
      username: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(20)], usernameValidator.validate.bind(usernameValidator)],
      password: ['', [Validators.required, Validators.pattern((this.passwordPatten))]],
      passwordConfirm: ['', [Validators.required]]
    }, { validators: this.checkPasswords });
  }

  formErrors = {
    'username': '',
    'email':'',
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
              const err = this.translate.instant(`${this.validationMessage}.${key}.${errorKey}`);
              this.formErrors[key] += err;
            }
          };
        }
      }
    })
  }

  private checkPasswords(group: FormGroup) {
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

  constructForm(formData = this.signForm) : FormGroup {
    return this.fb.group({
      email : [formData.value.email],
      user : this.fb.group({
        username : [formData.value.username],
        password : [formData.value.password]
      })
    })
  }

  register() {
    console.log(this.signForm.value.username);
    this.accountService.createAccount(this.constructForm(this.signForm)).subscribe(
      (data) => {
        alert("Account created with success : " + data.user.username)
        this.signForm.reset()
      },
      ((error) => { 

        console.log(error)
      })
  )};
}