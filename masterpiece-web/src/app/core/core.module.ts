import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { CreateAccountComponent } from './create-account/create-account.component';
import { ContactsComponent } from './contacts/contacts.component';
import { LogInComponent } from './log-in/log-in.component';

import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import { CustomUserComponent } from './custom-user/custom-user.component';
import { SubjectComponent } from './subject/subject.component';



@NgModule({
  declarations: [
    CustomUserComponent,
    CreateAccountComponent,
    ContactsComponent,
    LogInComponent,
    SubjectComponent],

  imports: [
    FormsModule,
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
    CustomUserComponent,
    CreateAccountComponent,
    ContactsComponent,
    LogInComponent
  ]
})
export class CoreModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}