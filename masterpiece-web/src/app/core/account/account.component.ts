import { Component, OnInit } from '@angular/core';
import { AccountRegistrationService } from '../account-registration.service';


@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers:[AccountRegistrationService]
})
export class AccountComponent implements OnInit {

  constructor(private service : AccountRegistrationService) { }

  ngOnInit() {
  }

  findOne(id:number) {
    return this.service.getAccount(id);
  }
}
