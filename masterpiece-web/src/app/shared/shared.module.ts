import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './page-not-found/not-found.component';
import { CustomErrorComponent } from './custom-error/custom-error.component';
import { AuthenticationService } from './authentication/authentication.service';

@NgModule({
  declarations: [NotFoundComponent, CustomErrorComponent],
  imports: [
    CommonModule
  ],
  exports: [
    
  ]
})
export class SharedModule { }
