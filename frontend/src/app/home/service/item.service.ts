import {Injectable} from "@angular/core";
import {Http, Headers, Response } from "@angular/http";
import {Observable} from "rxjs/Rx";import {AuthService} from "../../auth/service/auth.service";
import {Item} from "../../model/item";
import {LoginInfo} from "../../model/login.info";

@Injectable()
export class ItemService {

  constructor(private http: Http, private authService: AuthService) {
  }

  save(item: Item): Observable<boolean> {
    let loginInfo = this.authService.getLoginInfo();
    return this.http.post('http://localhost:8080/api/items', {
      item: item,
      username: loginInfo.username
    }, {
      headers: this.createAuthorizationHeader(loginInfo)
    }).map((response: Response) => {
        console.log('Response: ' + response);
        return true;
      }).catch((error:any) => Observable.throw(error.json().message || 'Server error'));
  }

  remove(item: Item): Observable<boolean> {
    let loginInfo = this.authService.getLoginInfo();
    return this.http.delete('http://localhost:8080/api/items/' + item.id + '/' + loginInfo.username, {
      headers: this.createAuthorizationHeader(loginInfo)
    }).map((response: Response) => {
        console.log('Response: ' + response);
        return true;
      }).catch((error:any) => Observable.throw(error.json().message || 'Server error'));
  }

  private createAuthorizationHeader(loginInfo: LoginInfo) {
    let headers = new Headers();
    headers.append('Authorization', loginInfo.token);
    headers.append('Content-Type', 'application/json');
    return headers;
  }

}
