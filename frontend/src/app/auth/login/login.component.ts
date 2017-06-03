import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

import {Message} from "primeng/components/common/api";

@Component({
    moduleId: module.id,
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    model: any;
    messages: Message[] = [];

    constructor(private router: Router) {
    }

    ngOnInit(): void {
      this.model = {
        email: 'oliverdudas@gmail.com',
        password: '123456'
      };
    }

    onSubmit(): void {
      console.log('submit');
    }
}
