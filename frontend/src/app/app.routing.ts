import {RouterModule, Routes} from "@angular/router";
import {ModuleWithProviders} from "@angular/core";
import {AuthComponent} from "./auth/auth.component";
import {HomeComponent} from "./home/home.component";

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: AuthComponent
  },
  {
    path: 'home',
    component: HomeComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
