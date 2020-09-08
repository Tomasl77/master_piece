import { Component, OnInit, Input, Optional, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { CustomUserRegistrationService } from '../custom-user-registration.service';
import { AccountDto } from './accountDto';


@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers:[CustomUserRegistrationService]
})
export class AccountComponent implements OnInit, OnDestroy {

  @Input('id') id:number;
  account: AccountDto;
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
      error=> console.log((error.error)));
  }
}
