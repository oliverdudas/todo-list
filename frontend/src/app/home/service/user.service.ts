import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {User} from "../../model/user";
import {AuthService} from "../../auth/service/auth.service";
import {LoginInfo} from "../../model/login.info";

@Injectable()
export class UserService {


  constructor(private http: Http, private authService: AuthService) {
  }

  getUser(): Observable<User> {
    let loginInfo = this.authService.getLoginInfo();
    return this.http.get('http://localhost:8080/api/user/' + loginInfo.username, {
      headers: this.createAuthorizationHeader(loginInfo)
    }).map(response => response.json())
  }

  private createAuthorizationHeader(loginInfo: LoginInfo) {
    let headers = new Headers();
    headers.append('Authorization', loginInfo.token);
    return headers;
  }

}
