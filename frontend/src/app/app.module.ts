import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AuthModule } from "./auth/auth.module";
import { routing } from "./app.routing";

import { AppComponent } from './app.component';
import {HomeModule} from "./home/home.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AuthModule,
    HomeModule,
    routing
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
