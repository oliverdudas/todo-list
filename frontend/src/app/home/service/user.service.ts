import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {User} from "../../model/user";
import {AuthService} from "../../auth/service/auth.service";

@Injectable()
export class UserService {

  constructor(private http: Http, private authService: AuthService) {
  }

  getUser(): Observable<User> {
    return this.http.get('api/users', {
      headers: this.authService.createAuthorizationHeader()
    }).map(response => response.json())
  }

}
