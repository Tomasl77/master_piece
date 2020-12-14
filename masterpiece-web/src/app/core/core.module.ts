import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatSelectModule, MatFormFieldModule } from '@angular/material';
import { AgGridModule } from 'ag-grid-angular';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { RouterModule } from '@angular/router'

import { CreateAccountComponent } from './create-account/create-account.component';
import { LogInComponent } from './log-in/log-in.component';
import { AccountComponent } from './account/account.component';
import { SubjectComponent } from './subject/subject.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { BtnCellRenderer } from '../shared/btn-cell-renderer.component';
import { SharingSessionComponent } from './sharing-session/sharing-session.component';
import { BtnCellRendererBis } from '../shared/btn-cell-renderer-bis.component';



@NgModule({
  declarations: [
    AccountComponent,
    CreateAccountComponent,
    LogInComponent,
    SubjectComponent,
    AdminPanelComponent,
    SharingSessionComponent],

  imports: [
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSelectModule,
    MatFormFieldModule,
    RouterModule,
    TranslateModule.forChild({
      loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
      }
  }),
  AgGridModule.withComponents([BtnCellRenderer, BtnCellRendererBis])
  ],
  
  exports: [
    AccountComponent,
    CreateAccountComponent,
    LogInComponent
  ]
})
export class CoreModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}