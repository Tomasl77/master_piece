import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutComponent } from './layout/layout.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule, MatIconModule, MatSidenavModule, MatListModule, MatButtonModule } from '@angular/material';
import { CoreModule } from '../core/core.module';
import { MaterialRoutingModule } from '../material/material-routing'
import { FlexLayoutModule } from '@angular/flex-layout';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { HttpClient } from '@angular/common/http';
import { RouterModule } from '@angular/router'

@NgModule({
  declarations: [
    LayoutComponent,
    NavbarComponent
  ],
  imports: [
    RouterModule,
    CommonModule,
    FlexLayoutModule,
    MatMenuModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    CoreModule,
    MaterialRoutingModule,
    TranslateModule.forChild({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  exports: [
    LayoutComponent,
    NavbarComponent
  ]
})
export class MaterialModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}