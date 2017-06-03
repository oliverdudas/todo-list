import {Routes, RouterModule} from "@angular/router";
import {ModuleWithProviders} from "@angular/core";
import {AuthComponent} from "./auth/auth.component";

const appRoutes: Routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    {
      path: 'login',
      component: AuthComponent
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
