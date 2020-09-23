import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './page-not-found/not-found.component';
import { CustomErrorComponent } from './custom-error/custom-error.component';
import { BtnCellRenderer } from './btn-cell-renderer.component';

@NgModule({
  declarations: [NotFoundComponent, CustomErrorComponent, BtnCellRenderer],
  imports: [
    CommonModule
  ],
  exports: [
    BtnCellRenderer,
  ]
})
export class SharedModule { }
