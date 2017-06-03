import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

import {Message} from "primeng/components/common/api";
import {AuthService} from "../auth/service/auth.service";
import {User} from "../model/user";

@Component({
    moduleId: module.id,
    selector: 'home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent {
}
