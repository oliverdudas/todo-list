import {NgModule} from "@angular/core";

import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";

import {TabViewModule} from "primeng/components/tabview/tabview";
import {GrowlModule} from "primeng/components/growl/growl";
import {InputTextModule, ButtonModule, DataListModule} from "primeng/primeng";

import {HttpModule} from "@angular/http";
import {HomeComponent} from "./home.component";
import {UserService} from "./service/user.service";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpModule,
    TabViewModule,
    InputTextModule,
    DataListModule,
    ButtonModule,
    GrowlModule
  ],
  providers: [UserService],
  declarations: [HomeComponent],
  exports: [HomeComponent]
})
export class HomeModule {
}
