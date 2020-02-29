import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountComponent } from '../core/account/account.component';
import { CreateAccountComponent } from '../core/create-account/create-account.component';
import { ContactsComponent } from '../core/contacts/contacts.component';
import { LogInComponent } from '../core/log-in/log-in.component';
import { NotFoundComponent } from '../shared/not-found/not-found.component'

const materialRoutes: Routes = [
  /*{
    path: '', redirectTo:'/login', pathMatch:'full'
  },
  {
    path: 'accounts', component: AccountComponent
  },
  {
    path: 'create-account', component: CreateAccountComponent
  },
  {
    path: 'contacts', component: ContactsComponent
  },
  {
    path: 'login', component: LogInComponent
  }, 
  {
    path: 'not-found', component: NotFoundComponent
  },
  {
    path: '**', redirectTo : '/not-found', pathMatch : "full" 
  }*/
];

@NgModule({
  imports: [RouterModule.forChild(materialRoutes)],
  exports: [RouterModule]
})
export class MaterialRoutingModule { }