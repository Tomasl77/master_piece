import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './page-not-found/not-found.component';
import { CustomErrorComponent } from './custom-error/custom-error.component';
import { BtnCellRenderer } from './btn-cell-renderer.component';
import { MatDialogModule, MatFormField, MatFormFieldModule } from '@angular/material';
import { DateTimeDialogComponentComponent } from './modals/date-time-dialog-component/date-time-dialog-component.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule, NgxMatNativeDateModule } from '@angular-material-components/datetime-picker';
@NgModule({
  declarations: [
    NotFoundComponent,
    CustomErrorComponent,
    BtnCellRenderer,
    DateTimeDialogComponentComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatDatepickerModule,
    NgxMatDatetimePickerModule, 
    NgxMatTimepickerModule, 
    NgxMatNativeDateModule
  ],
  exports: [
    BtnCellRenderer,
  ],
  entryComponents: [
    DateTimeDialogComponentComponent
  ]
})
export class SharedModule { }
