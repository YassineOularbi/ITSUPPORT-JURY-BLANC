import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/login/login.component';
import { HomeComponent } from './features/auth/home/home.component';
import { AdminComponent } from './features/auth/admin/admin.component';
import { authGuard } from './core/guards/auth.guard';
import { Role } from './core/enums/role.enum';
import { ClientComponent } from './features/auth/client/client.component';
import { TechnicianComponent } from './features/auth/technician/technician.component';
import { LogoutComponent } from './features/auth/logout/logout.component';

export const routes: Routes = [
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
