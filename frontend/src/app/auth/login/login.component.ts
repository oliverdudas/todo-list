import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

import {Message} from "primeng/components/common/api";
import {AuthService} from "../service/auth.service";
import {User} from "../../model/user";

@Component({
    moduleId: module.id,
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    model: User;
    messages: Message[] = [];

    constructor(private router: Router,
      private authService: AuthService) {
    }

    login() {
      this.authService.login(this.model.username, this.model.password)
        .subscribe(result => {
          if (result === true) {
            console.log('Successfully logged in!');
            this.router.navigate(['home']);
          } else {
            console.log('Login failed!');
            this.messages.push({severity: 'error', summary: 'Username or password is incorrect!'});
          }
        }, error => {
          this.messages = [];
          this.messages.push({severity: 'error', summary: error});
        });
    }

    ngOnInit(): void {
      this.model = new User();
    }

    onSubmit(): void {
      this.login();
    }
}
