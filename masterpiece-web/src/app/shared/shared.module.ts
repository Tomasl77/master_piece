import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './page-not-found/not-found.component';
import { CustomErrorComponent } from './custom-error/custom-error.component';
import { BtnCellRenderer } from './btn-cell-renderer.component';
import { ConfirmationModalComponent } from './modals/confirmation-modal/confirmation-modal.component';
import { MatDialogModule } from '@angular/material';

@NgModule({
  declarations: [
    NotFoundComponent,
    CustomErrorComponent,
    BtnCellRenderer
  ],
  imports: [
    CommonModule,
    MatDialogModule
  ],
  exports: [
    BtnCellRenderer,
  ]
})
export class SharedModule { }
