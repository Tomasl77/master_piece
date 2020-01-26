import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AccountComponent } from './account/account.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { ContactsComponent } from './contacts/contacts.component';
import { LogInComponent } from './log-in/log-in.component';



@NgModule({
  declarations: [
    AccountComponent,
    CreateAccountComponent,
    ContactsComponent,
    LogInComponent],

  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule
  ],

  exports: [
    AccountComponent,
    CreateAccountComponent,
    ContactsComponent,
    LogInComponent
  ]
})
export class CoreModule { }
