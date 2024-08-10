import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/components/login/login.component';
import { HomeComponent } from './features/home/home.component';
import { AdminComponent } from './shared/components/admin/admin.component';
import { authGuard } from './core/guards/auth.guard';
import { Role } from './core/enums/role.enum';
import { ClientComponent } from './shared/components/client/client.component';
import { TechnicianComponent } from './shared/components/technician/technician.component';
import { LogoutComponent } from './features/auth/components/logout/logout.component';
import { AuthComponent } from './features/auth/auth.component';

export const routes: Routes = [
    {
        path: '',
        component: AuthComponent
    },
    {
        path: 'login',
        component: LoginComponent,
        // canActivate: [authGuard] 
    },
    {
        path: 'logout',
        component: LogoutComponent,
        // canActivate: [authGuard]
    },
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [authGuard]
    },
    {
        path: 'admin',
        component: AdminComponent,
        canActivate: [authGuard], 
        data: { roles: Role[Role.ADMIN] }
    },
    {
        path: 'client',
        component: ClientComponent,
        canActivate: [authGuard],
        data: { roles: Role[Role.CLIENT] }
    },
    {
        path: 'technician',
        component: TechnicianComponent,
        canActivate: [authGuard],
        data: { roles: Role[Role.TECHNICIAN] }
    }
];
