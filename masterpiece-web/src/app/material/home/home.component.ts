import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/shared/authentication/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private translate: TranslateService, 
    private router: Router, 
    private authenticationService: AuthenticationService
    ) { }

  ngOnInit() {
  }

public isActive(url : string) {
  return this.router.url.includes('/subject');
}

  useLanguage(language: string) {
    this.translate.use(language);
  }

  isAuthenticated() {
    return this.authenticationService.isAuthenticated();
  }

  /** 
  Need to explicitly return both currentUserValue and isAdmin().
  If only isAdmin() is return, if there's no logged in user = > undefined currentUserValue
  */
  isAdmin() : boolean{
    return this.authenticationService.currentUserValue && this.authenticationService.currentUserValue.isAdmin();
  }

  logout() {
    this.authenticationService.logout();
  }
}
