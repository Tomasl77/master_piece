import { Component, OnInit, Input, Optional, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { CursorError } from '@angular/compiler/src/ml_parser/lexer';
import { CustomErrorComponent } from 'src/app/shared/custom-error/custom-error.component';
import { CustomUser } from '../custom-user';
import { CustomUserRegistrationService } from '../custom-user-registration.service';


@Component({
  selector: 'app-account',
  templateUrl: './custom-user.component.html',
  styleUrls: ['./custom-user.component.css'],
  providers:[CustomUserRegistrationService]
})
export class CustomUserComponent implements OnInit, OnDestroy {

  @Input('id') id:number;
  account: CustomUser;
  error: any;
  private accountSubscription : Subscription;

  constructor(private service : CustomUserRegistrationService) { }
  
  ngOnDestroy(): void {
    if(this.accountSubscription) {
      this.accountSubscription.unsubscribe
    }
  }

  ngOnInit() {

  }

  findOne() {
    this.accountSubscription = this.service.getAccount(this.id).subscribe(
      account => this.account=account,
      error=> console.log(JSON.stringify(error.error)));
  }
}
