import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Token } from '../token';
import { AuthenticationService } from './authentication.service';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

    constructor(private router: Router, private authentService: AuthenticationService) { }

    canActivate(route: ActivatedRouteSnapshot): boolean {
        const token: Token = JSON.parse(localStorage.getItem("token"));
        if (token && token.expiresIn >= 0) {
            return true;
        }
        this.router.navigate(['/login'])
        return false
    }
}