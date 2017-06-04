import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {AuthService} from "../../auth/service/auth.service";
import {Item} from "../../model/item";

@Injectable()
export class ItemService {

  constructor(private http: Http, private authService: AuthService) {
  }

  save(item: Item): Observable<boolean> {
    return this.http.post('api/items', item, {
      headers: this.authService.createAuthorizationHeader()
    }).map((response: Response) => {
      console.log('Response: ' + response);
      return true;
    }).catch(this.handleError());
  }

  update(item: Item): Observable<boolean> {
    return this.http.put('api/items', item, {
      headers: this.authService.createAuthorizationHeader()
    }).map((response: Response) => {
      console.log('Response: ' + response);
      return true;
    }).catch(this.handleError());
  }

  remove(item: Item): Observable<boolean> {
    return this.http.delete('api/items/' + item.id, {
      headers: this.authService.createAuthorizationHeader()
    }).map((response: Response) => {
      console.log('Response: ' + response);
      return true;
    }).catch(this.handleError());
  }

  private handleError() {
    return (error: any) => Observable.throw(error.json().message || 'Server error');
  }

}
