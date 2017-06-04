import {Injectable} from "@angular/core";
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from "@angular/router";
import {AuthService} from "./auth.service";

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private authService: AuthService,
                private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        if(this.authService.isLoggedIn) {
          console.log('AuthGuard: navigation allowed to ' + route);
          return true;
        } else {
            console.log('AuthGuard: navigation rejected to ' + route + '. Redirecting back to login.');
            this.router.navigate(['/login']);
            return false
        }
    }
}
