import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

import {Message} from "primeng/components/common/api";
import {AuthService} from "../auth/service/auth.service";
import {User} from "../model/user";
import {UserService} from "./service/user.service";

@Component({
    moduleId: module.id,
    selector: 'home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  model: User;
  messages: Message[] = [];

  constructor(private router: Router,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.model = new User();
    this.userService.getUser()
      .subscribe(user => {
        this.model = user;
      });
  }

}
