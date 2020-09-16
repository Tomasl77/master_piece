import { Component, OnInit, Input, Optional, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/shared/authentication/authentication.service';
import { Member } from 'src/app/shared/models/member.model';
import { MemberRegistrationService } from '../member-registration.service';


@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers:[MemberRegistrationService]
})
export class AccountComponent implements OnInit, OnDestroy {

  id:number;
  account: Member;
  error: any;
  private accountSubscription : Subscription;

  constructor(private service : MemberRegistrationService, private authenthicationService: AuthenticationService) { }
  
  ngOnInit() {
    this.id = this.authenthicationService.currentUserValue.userId;
    this.accountSubscription = this.service.getAccount(this.id).subscribe(
      account => {
        this.account=account,
        console.log(this.account)
      },
      error=> console.log((error.error)));
  }

  ngOnDestroy(): void {
    this.service.unsubscribe(this.accountSubscription);
  }
}
