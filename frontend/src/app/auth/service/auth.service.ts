import {Injectable} from "@angular/core";
import {Http, Headers, Response } from "@angular/http";
import {Observable} from "rxjs/Rx";

@Injectable()
export class AuthService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {
  }

  login(username: string, password: string): Observable<boolean> {
    return this.http.post('http://localhost:8080/user/login', JSON.stringify({username: username, password: password}))
      .map((response: Response) => {
        // login successful if there's a jwt token in the response
        let token = response.headers && response.headers.get('authorization');
        if (token) {
          console.log('Token successfully retrieved: ' + token);
          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));
          console.log('Token stored in localStorage under key: currentUser');

          // return true to indicate successful login
          return true;
        } else {
          console.log('Token retrieval failure!');
          // return false to indicate failed login
          return false;
        }
      }).catch((error:any) => Observable.throw(error.json().error + '\n' + error.json().message || 'Server error'));
  }

  getToken(): String {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var token = currentUser && currentUser.token;
    return token ? token : "";
  }

}
