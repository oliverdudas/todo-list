import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {User} from "../../model/user";
import {AuthService} from "../../auth/service/auth.service";
import {environment} from "environments/environment"

@Injectable()
export class UserService {

  constructor(private http: Http, private authService: AuthService) {
  }

  getUser(): Observable<User> {
    return this.http.get(environment.apiUsersUrl, {
      headers: this.authService.createAuthorizationHeader()
    }).map(response => response.json())
  }

}
