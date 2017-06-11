import {Injectable} from "@angular/core";
import {Http, Headers, Response } from "@angular/http";
import {Observable} from "rxjs/Rx";
import {LoginInfo} from "../../model/login.info";
import {environment} from "environments/environment"

@Injectable()
export class AuthService {

  private headers = new Headers({'Content-Type': 'application/json'});
  isLoggedIn: boolean = false;

  constructor(private http: Http) {
  }

  login(username: string, password: string): Observable<boolean> {
    return this.http.post(environment.authLoginUrl, JSON.stringify({username: username, password: password}), {headers: this.headers})
      .map((response: Response) => {
        console.log('Response: ' + response);
        let token = response.headers && response.headers.get('authorization');
        console.log('headers: ' + response.headers);
        if (token) {
          console.log('Token successfully retrieved: ' + token);

          localStorage.setItem('currentUser', JSON.stringify({username: username, token: token}));
          console.log('Token stored in localStorage under key: currentUser');

          this.isLoggedIn = true;
        } else {
          console.log('Token retrieval failure!');
          this.isLoggedIn = false;
        }

        return this.isLoggedIn;
      }).catch((error:any) => Observable.throw(error.json().message || 'Server error'));
  }

  logOut(): Observable<boolean> {
    this.isLoggedIn = false;
    return Observable.of(false);
  }

  getLoginInfo(): LoginInfo {
    console.log('LoginInfo requested: ' + localStorage.getItem('currentUser'));
    let loginInfo = JSON.parse(localStorage.getItem('currentUser'));
    return new LoginInfo(loginInfo.username, loginInfo.token);
  }

  createAuthorizationHeader() {
    let headers = new Headers();
    headers.append('Authorization', this.getLoginInfo().token);
    headers.append('Content-Type', 'application/json');
    return headers;
  }

}
