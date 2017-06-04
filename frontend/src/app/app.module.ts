import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AuthModule } from "./auth/auth.module";
import { routing } from "./app.routing";

import { AppComponent } from './app.component';
import {HomeModule} from "./home/home.module";
import {ItemService} from "./home/service/item.service";
import {ButtonModule} from "primeng/primeng";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AuthModule,
    HomeModule,
    ButtonModule,
    routing
  ],
  providers: [ItemService],
  bootstrap: [AppComponent]
})
export class AppModule { }
