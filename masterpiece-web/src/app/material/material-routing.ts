import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomUserComponent } from '../core/custom-user/custom-user.component';
import { CreateAccountComponent } from '../core/create-account/create-account.component';
import { ContactsComponent } from '../core/contacts/contacts.component';
import { LogInComponent } from '../core/log-in/log-in.component';
import { NotFoundComponent } from '../shared/page-not-found/not-found.component'

const materialRoutes: Routes = [
 
];

@NgModule({
  imports: [RouterModule.forChild(materialRoutes)],
  exports: [RouterModule]
})
export class MaterialRoutingModule { }