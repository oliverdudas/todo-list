import {NgModule} from "@angular/core";

import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";

import {AuthComponent} from "./auth.component";

import {TabViewModule} from "primeng/components/tabview/tabview";
import {InputTextModule, ButtonModule} from "primeng/primeng";

import {GrowlModule} from "primeng/components/growl/growl";
import {HttpModule} from "@angular/http";

import {LoginComponent} from "./login/login.component";
import {AuthService} from "./service/auth.service";
import {HomeModule} from "../home/home.module";

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        HttpModule,
        TabViewModule,
        ButtonModule,
        InputTextModule,
        GrowlModule,
        HomeModule
    ],
    providers: [AuthService],
    declarations: [AuthComponent, LoginComponent],
    exports: [AuthComponent]
})
export class AuthModule {
}
