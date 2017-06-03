    import {NgModule} from "@angular/core";

import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";

import {AuthComponent} from "./auth.component";

import {TabViewModule} from "primeng/components/tabview/tabview";
import {ButtonModule} from "primeng/components/button/button";

import {GrowlModule} from "primeng/components/growl/growl";
import {HttpModule} from "@angular/http";

import {LoginComponent} from "./login/login.component";

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        HttpModule,
        TabViewModule,
        ButtonModule,
        GrowlModule
    ],
    providers: [],
    declarations: [AuthComponent, LoginComponent],
    exports: [AuthComponent]
})
export class AuthModule {
}
