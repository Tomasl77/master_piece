import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './shared/page-not-found/not-found.component'
import { CreateAccountComponent } from './core/create-account/create-account.component';
import { ContactsComponent } from './core/contacts/contacts.component';
import { LogInComponent } from './core/log-in/log-in.component';
import { CustomErrorComponent } from './shared/custom-error/custom-error.component';
import { CustomUserComponent } from './core/custom-user/custom-user.component';
import { SubjectComponent } from './core/subject/subject.component';
import { AuthGuard } from './shared/services/auth-guard';


const routes: Routes = [
  {
    path: '', redirectTo: '/login', pathMatch: 'full'
  },
  {
    path: 'accounts', component: CustomUserComponent, canActivate : [AuthGuard]
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
    path: 'subject', component: SubjectComponent, canActivate: [AuthGuard]
  },
  {
    path: 'not-found', component: NotFoundComponent
  },
  {
    path: 'error', component: CustomErrorComponent
  },
  {
    path: '**', redirectTo: '/not-found', pathMatch: "full"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
