import { Component, OnInit, Input, Optional, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { MemberRegistrationService } from '../member-registration.service';
import { AccountDto } from './accountDto';


@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers:[MemberRegistrationService]
})
export class AccountComponent implements OnInit, OnDestroy {

  @Input('id') id:number;
  account: AccountDto;
  error: any;
  private accountSubscription : Subscription;

  constructor(private service : MemberRegistrationService) { }
  
  ngOnDestroy(): void {
    if(this.accountSubscription) {
      this.accountSubscription.unsubscribe
    }
  }

  ngOnInit() {
   
  }

  findOne() {
    this.accountSubscription = this.service.getAccount(this.id).subscribe(
      account => {
        this.account=account,
        console.log(this.account)
      },
      error=> console.log((error.error)));
  }
}
