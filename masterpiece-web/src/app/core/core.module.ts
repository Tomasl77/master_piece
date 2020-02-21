import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { AccountComponent } from './account/account.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { ContactsComponent } from './contacts/contacts.component';
import { LogInComponent } from './log-in/log-in.component';

import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';



@NgModule({
  declarations: [
    AccountComponent,
    CreateAccountComponent,
    ContactsComponent,
    LogInComponent],

  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    TranslateModule.forChild({
      loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
      }
  })
  ],

  exports: [
    AccountComponent,
    CreateAccountComponent,
    ContactsComponent,
    LogInComponent
  ]
})
export class CoreModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}