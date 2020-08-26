import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Token } from '../models/token.model';
import { AuthenticationService } from './authentication.service';
import { Injectable } from '@angular/core';
import { TokenStorageService } from '../token-storage.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

    constructor(private router: Router, private authentService: AuthenticationService, private tokenService : TokenStorageService) { }

    canActivate(route: ActivatedRouteSnapshot): boolean {
        const token: Token = this.tokenService.getToken();
        if (token) {
            return true;
        }
        this.router.navigate(['/login'])
        return false;
    }
}