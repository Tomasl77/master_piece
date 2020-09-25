import { Component, OnInit, Input, Optional, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/shared/authentication/authentication.service';
import { CustomUser } from 'src/app/shared/models/custom-user.model';
import { UserRegistrationService } from '../user-registration.service';


@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers:[UserRegistrationService]
})
export class AccountComponent implements OnInit, OnDestroy {

  id:number;
  account: CustomUser;
  error: any;
  private accountSubscription : Subscription;

  constructor(private service : UserRegistrationService, private authenthicationService: AuthenticationService) { }
  
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
