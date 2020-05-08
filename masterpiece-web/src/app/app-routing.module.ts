import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './shared/page-not-found/not-found.component'
import { AccountComponent } from './core/account/account.component';
import { CreateAccountComponent } from './core/create-account/create-account.component';
import { ContactsComponent } from './core/contacts/contacts.component';
import { LogInComponent } from './core/log-in/log-in.component';


const routes: Routes = [
  {
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
