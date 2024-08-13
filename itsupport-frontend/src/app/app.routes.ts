import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/components/login/login.component';
import { HomeComponent } from './features/home/home.component';
import { authGuard } from './core/guards/auth.guard';
import { AuthComponent } from './features/auth/auth.component';
import { SignupComponent } from './features/auth/components/signup/signup.component';
import { DashboardComponent } from './features/dashboard/dashboard.component';
import { Role } from './core/enums/role.enum';

export const routes: Routes = [
    {
        path: '',
        component: AuthComponent,
        children: [
          { path: '', redirectTo: 'login', pathMatch: 'full' },
          { path: 'login', component: LoginComponent },
          { path: 'signup', component: SignupComponent}
        ]
    },
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [authGuard]
    },
    {
        path: 'dashboard',
        component: DashboardComponent,
        canActivate: [authGuard],
        data: { roles: [Role.ADMIN, Role.CLIENT, Role.TECHNICIAN] }
    },
];
