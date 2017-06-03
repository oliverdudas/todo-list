import {NgModule} from "@angular/core";

import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";

import {TabViewModule} from "primeng/components/tabview/tabview";
import {ButtonModule} from "primeng/components/button/button";

import {GrowlModule} from "primeng/components/growl/growl";
import {HttpModule} from "@angular/http";
import {HomeComponent} from "./home.component";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpModule,
    TabViewModule,
    ButtonModule,
    GrowlModule
  ],
  declarations: [HomeComponent],
  exports: [HomeComponent]
})
export class HomeModule {
}
