import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './page-not-found/not-found.component';
import { BtnCellRenderer } from './btn-cell-renderer.component';
import { MatToolbarModule, MatIconModule, MatSidenavModule, MatListModule, MatButtonModule, MatInputModule, MatFormFieldModule, MatDialogModule, MatMenuModule } from '@angular/material';import { DateTimeDialogComponentComponent } from './modals/date-time-dialog-component/date-time-dialog-component.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule, NgxMatNativeDateModule } from '@angular-material-components/datetime-picker';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { NavbarComponent } from './navbar/navbar.component';
import { LayoutComponent } from './layout/layout.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NavbarRoutingModule } from './navbar-routing.module';
import { HttpClient } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { RouterModule } from '@angular/router'

@NgModule({
  declarations: [
    NotFoundComponent,
    BtnCellRenderer,
    DateTimeDialogComponentComponent,
    LayoutComponent,
    NavbarComponent
  ],
  imports: [
    RouterModule,
    FlexLayoutModule,
    CommonModule,
    MatDialogModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    NgxMatDatetimePickerModule, 
    NgxMatTimepickerModule, 
    NgxMatNativeDateModule,
    TranslateModule,
    MatIconModule,
    NavbarRoutingModule,
    TranslateModule.forChild({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  exports: [
    BtnCellRenderer,
    LayoutComponent,
    NavbarComponent
  ],
  entryComponents: [
    DateTimeDialogComponentComponent
  ]
})
export class SharedModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
