import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const materialRoutes: Routes = [
 
];

@NgModule({
  imports: [RouterModule.forChild(materialRoutes)],
  exports: [RouterModule]
})
export class NavbarRoutingModule { }