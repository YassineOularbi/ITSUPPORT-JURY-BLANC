import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/components/login/login.component';
import { HomeComponent } from './features/home/home.component';
import { AuthComponent } from './features/auth/auth.component';
import { SignupComponent } from './features/auth/components/signup/signup.component';
import { DashboardComponent } from './features/dashboard/layout/dashboard.component';
import { Role } from './core/enums/role.enum';
import { UserListComponent } from './shared/components/user/user-list/user-list.component';
import { UserComponent } from './shared/layouts/user/user.component';
import { authGuard } from './core/guards/auth.guard';
import { roleGuard } from './core/guards/role.guard';
import { AddUpdateUserComponent } from './shared/components/user/add-update-user/add-update-user.component';
import { ErrorPageComponent } from './shared/components/error-page/error-page.component';
import { EquipmentComponent } from './shared/layouts/equipment/equipment.component';
import { EquipmentListComponent } from './shared/components/equipment/equipment-list/equipment-list.component';
import { AddUpdateEquipmentComponent } from './shared/components/equipment/add-update-equipment/add-update-equipment.component';

export const routes: Routes = [
    {
      path: '',
      component: AuthComponent,
      children: [
        {
          path: '',
          redirectTo: 'login',
          pathMatch: 'full'
        },
        { 
          path: 'login', 
          component: LoginComponent 
        },
        { 
          path: 'signup', 
          component: SignupComponent 
        }
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
      canActivateChild: [roleGuard],
      data: { roles: [Role.ADMIN, Role.CLIENT, Role.TECHNICIAN] },
      children: [
        {
            path: 'error',
            component: ErrorPageComponent,
            canActivate: [authGuard]
        },
        {
          path: 'equipments',
          component: EquipmentComponent,
          canActivate: [authGuard],
          canActivateChild: [roleGuard],
          data: { roles: [Role.ADMIN, Role.CLIENT]},
          children: [
            {
              path: '',
              redirectTo: 'all',
              pathMatch: 'full'
            },
            {
              path: 'add',
              component: AddUpdateEquipmentComponent,
              canActivate: [authGuard],
              data: { roles: [Role.ADMIN]},
            },
            {
              path: ':type',
              component: EquipmentListComponent,
              canActivate: [authGuard],
              data: { roles: [Role.ADMIN, Role.CLIENT]},
            },
            {
              path: 'edit/:id',
              component: AddUpdateEquipmentComponent,
              canActivate: [authGuard],
              data: { roles: [Role.ADMIN]},
            }
          ]
        },
        {
          path: 'users',
          component: UserComponent,
          canActivateChild: [roleGuard],
          data: { roles: [Role.ADMIN] },
          children: [
            {
              path: '',
              redirectTo: 'admin',
              pathMatch: 'full'
            },
            {
              path: 'add/:type',
              component: AddUpdateUserComponent,
              canActivate: [authGuard],
              data: { roles: [Role.ADMIN] }
            },
            {
              path: 'edit/:type/:id',
              component: AddUpdateUserComponent,
              canActivate: [authGuard],
              data: { roles: [Role.ADMIN] }
            },
            {
                path: ':type',
                component: UserListComponent,
                canActivate: [authGuard],
                data: { roles: [Role.ADMIN] }
              }
          ]
        }
      ]
    }
  ];
  