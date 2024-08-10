import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanActivateFn } from '@angular/router';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Store } from '@ngrx/store';
import { AppState } from '../ngrx/app.state';
import { selectRole } from '../ngrx/auth.selectors';
import { AuthService } from '../services/auth.service';
import { Role } from '../enums/role.enum';

export const authGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
): Observable<boolean> => {
  const store = inject(Store<AppState>);
  const authService = inject(AuthService);
  const router = inject(Router);

  const url: string = state.url;
  const requiredRoles = route.data['roles'] as Role[] | undefined;

  if (authService.isLoggedIn()) {
    // if (url === '/login') {
    //   router.navigate(['/home']);
    //   return of(false);
    // }
    return store.select(selectRole).pipe(
      map(userRole => {
        if (requiredRoles && userRole && !requiredRoles.includes(userRole)) {
          router.navigate(['/home']);
          return false;
        }
        return true;
      })
    );
  } else {
    // if (url !== '/login') {
    //   router.navigate(['/login'], { queryParams: { returnUrl: url } });
    //   return of(false);
    // }
    // return of(true);
    router.navigate(['/login'], { queryParams: { returnUrl: url } });
    return of(false);
  }
};
