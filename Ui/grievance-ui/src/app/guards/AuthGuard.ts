import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { LoginService } from '../services/login.service';


@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router 
    ) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {  4
        const currentUser = localStorage.getItem('CurrentUser');
        if (currentUser) {
            this.router.navigate(['/user']);
            return true;
        }else{
            this.router.navigate(['/login']);
            return false;
        } 
    }
}