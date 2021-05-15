import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardsComponent } from './cards/cards.component';
import { MatCardModule } from '@angular/material';



@NgModule({
  declarations: [CardsComponent],
  imports: [
    CommonModule,
    MatCardModule
  ],
  exports: [CardsComponent]
})
export class CardModule { }
