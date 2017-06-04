import {NgModule} from "@angular/core";

import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";

import {TabViewModule} from "primeng/components/tabview/tabview";
import {GrowlModule} from "primeng/components/growl/growl";
import {InputTextModule, ButtonModule, DataListModule, DialogModule} from "primeng/primeng";

import {HttpModule} from "@angular/http";
import {HomeComponent} from "./home.component";
import {UserService} from "./service/user.service";
import {ItemService} from "./service/item.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpModule,
    TabViewModule,
    InputTextModule,
    DataListModule,
    ButtonModule,
    BrowserAnimationsModule,
    DialogModule,
    GrowlModule
  ],
  providers: [UserService, ItemService],
  declarations: [HomeComponent],
  exports: [HomeComponent]
})
export class HomeModule {
}
