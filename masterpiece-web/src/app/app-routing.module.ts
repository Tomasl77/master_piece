import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './shared/page-not-found/not-found.component'
import { CreateAccountComponent } from './core/create-account/create-account.component';
import { ContactsComponent } from './core/contacts/contacts.component';
import { LogInComponent } from './core/log-in/log-in.component';
import { CustomErrorComponent } from './shared/custom-error/custom-error.component';
import { AccountComponent } from './core/account/account.component';
import { SubjectComponent } from './core/subject/subject.component';
import { AuthGuard } from './shared/authentication/auth-guard';
import { AdminPanelComponent } from './core/admin-panel/admin-panel.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'accounts', component: AccountComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN", "ROLE_USER"] } },
  { path: 'create-account', component: CreateAccountComponent },
  { path: 'contacts', component: ContactsComponent },
  { path: 'login', component: LogInComponent },
  { path: 'subject', component: SubjectComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN", "ROLE_USER"] } },
  { path: "admin-panel", component : AdminPanelComponent, canActivate: [AuthGuard], data : { roles : ["ROLE_ADMIN"] } },
  { path: 'not-found', component: NotFoundComponent },
  { path: 'error', component: CustomErrorComponent },
  { path: '**', redirectTo: '/not-found', pathMatch: "full" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
