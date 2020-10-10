import { Component, OnInit, Input, Optional, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/shared/authentication/authentication.service';
import { CustomUser } from 'src/app/shared/models/custom-user.model';
import { UserRegistrationService } from '../user-registration.service';
import { EmailValidator } from '../validators/email-validator';


@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers:[UserRegistrationService]
})
export class AccountComponent implements OnInit, OnDestroy {

  private readonly validationMessage : string = "account.validationMessages";

  isEmailValid: boolean;
  id:number;
  account: CustomUser;
  error: any;
  private accountSubscription : Subscription;
  private updateUserForm: FormGroup;
  private newMail: string;

  formErrors = {
    'email':''
  }

  constructor(
    private userService : UserRegistrationService,
    private authenthicationService: AuthenticationService,
    private fb: FormBuilder,
    private translate : TranslateService,
    private emailValidator: EmailValidator
    ) {
    this.updateUserForm = this.fb.group({
      email:['', [Validators.email, Validators.maxLength(255)], this.emailValidator.validate.bind(this.emailValidator)],
    })
  };
  
  ngOnInit() {
    this.id = this.authenthicationService.currentUserValue.userId;
    this.accountSubscription = this.userService.getAccount(this.id).subscribe(
      account => {
        this.account=account,
        console.log(this.account)
      },
      error=> console.log((error.error)
      )
    );
    this.updateUserForm.valueChanges.subscribe(() => {
      this.logValidationErrors(this.updateUserForm)
    });
  }

  ngOnDestroy(): void {
    this.userService.unsubscribe(this.accountSubscription);
  }

  logValidationErrors(group: FormGroup = this.updateUserForm): void {
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

  modify() {
    console.log(this.updateUserForm);
    this.userService.updateUser(this.updateUserForm).subscribe(
      (data :CustomUser) => {
        console.log(data.info.email);
        this.newMail = "Your email has been changed. New mail : " + data.info.email;
        setTimeout(()=> {
          this.newMail = null
        }, 2000)
        this.ngOnInit();
        this.updateUserForm.reset();
      }, 
      error => console.log("error : "+ JSON.stringify(error))
    );
  }
}