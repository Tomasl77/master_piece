import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './page-not-found/not-found.component';
import { BtnCellRenderer } from './btn-cell-renderer.component';
import { MatDialogModule, MatInputModule ,MatFormFieldModule } from '@angular/material';
import { DateTimeDialogComponentComponent } from './modals/date-time-dialog-component/date-time-dialog-component.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule, NgxMatNativeDateModule } from '@angular-material-components/datetime-picker';
import { TranslateModule } from '@ngx-translate/core';
@NgModule({
  declarations: [
    NotFoundComponent,
    BtnCellRenderer,
    DateTimeDialogComponentComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    NgxMatDatetimePickerModule, 
    NgxMatTimepickerModule, 
    NgxMatNativeDateModule,
    TranslateModule
  ],
  exports: [
    BtnCellRenderer,
  ],
  entryComponents: [
    DateTimeDialogComponentComponent
  ]
})
export class SharedModule { }
