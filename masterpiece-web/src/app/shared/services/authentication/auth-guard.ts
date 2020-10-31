import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';
import { Injectable } from '@angular/core';
import { TokenStorageService } from '../../token-storage.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

    constructor(private router: Router, private authentService: AuthenticationService, private tokenService : TokenStorageService) { }

    canActivate(route: ActivatedRouteSnapshot): boolean {
        const currentUser = this.authentService.currentUserValue;
        if (currentUser) {
            if(!route.data.roles || currentUser.checkRole(route.data.roles))
            return true;
        }
        this.router.navigate(['/login'])
        return false;
    }
}