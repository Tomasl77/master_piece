import { Component, OnInit, Input, Optional, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { CustomUserRegistrationService } from '../custom-user-registration.service';
import { CustomUserDto } from './CustomUserDto';


@Component({
  selector: 'app-account',
  templateUrl: './custom-user.component.html',
  styleUrls: ['./custom-user.component.css'],
  providers:[CustomUserRegistrationService]
})
export class CustomUserComponent implements OnInit, OnDestroy {

  @Input('id') id:number;
  account: CustomUserDto;
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
