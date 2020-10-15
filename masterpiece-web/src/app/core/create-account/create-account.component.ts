import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UsernameValidator } from '../validators/username-validator';
import { TranslateService } from '@ngx-translate/core';
import { UserRegistrationService } from '../user-registration.service';
import { LogInComponent } from '../log-in/log-in.component'
import { Config } from 'src/assets/config-properties';
import { EmailValidator } from '../validators/email-validator';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css'],
  providers: [UserRegistrationService, LogInComponent]
})
export class CreateAccountComponent implements OnInit {

  private readonly validationMessage : string = "create-account.validationMessages";

  public signForm: FormGroup;

  private formToReturn : FormGroup;

  constructor(
    private fb: FormBuilder, 
    private usernameValidator: UsernameValidator,
    private translate: TranslateService, 
    private accountService : UserRegistrationService, 
    private logInComponent : LogInComponent,
    private emailValidator: EmailValidator
    ) {
    this.signForm = this.fb.group({
      email:['', [Validators.required, Validators.email, Validators.maxLength(255)], this.emailValidator.validate.bind(this.emailValidator)],
      username: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(20)], this.usernameValidator.validate.bind(this.usernameValidator)],
      password: ['', [Validators.required, Validators.pattern((Config.passwordPattern))]],
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
      credentials : this.fb.group({
        username : [formData.value.username],
        password : [formData.value.password],
      })
    })
  }

  register() {
    const username : string = this.signForm.value.username;
    const pwd : string = this.signForm.value.password;
    this.accountService.createAccount(this.constructForm(this.signForm)).subscribe(
      () => {
        this.logInComponent.createAndLogIn(username, pwd);
        this.signForm.reset()
      },
      ((error) => { 
        console.log(error)
      })
  )};
}