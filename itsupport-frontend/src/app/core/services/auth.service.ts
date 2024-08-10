import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, pipe, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { environment } from '../../../environments/environment';
import { LoginException } from '../exceptions/login.exception';
import { LoginRequest } from '../interfaces/login-request.interface';
import { AuthResponse } from '../interfaces/auth-response.interface';
import { RegisterRequest } from '../interfaces/register-request.interface';
import { JwtService } from './jwttoken.service';
import { UserDto } from '../dtos/user-dto.model';
import { logout, setRole, setUser } from '../ngrx/auth.actions';
import { Store } from '@ngrx/store';
import { Role } from '../enums/role.enum';
import { AppState } from '../ngrx/app.state';
import { selectRole, selectUser } from '../ngrx/auth.selectors';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  user$: Observable<UserDto | null>

  role$: Observable<Role | null>

  private apiUrl = `${environment.apiUrl}/api/auth`;



  constructor(
    private http: HttpClient,
    private jwtService: JwtService,
    private store: Store<AppState>
  ) {
    this.role$ = this.store.select((pipe(selectRole)));
    this.user$ = this.store.select((pipe(selectUser)));
  }

  login(loginRequest: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, loginRequest).pipe(
      map(response => {
        const authResponse: AuthResponse = response;
        if (authResponse.token && this.jwtService.validateToken(authResponse.token, loginRequest)) {

          localStorage.setItem("auth-token", authResponse.token)

          return authResponse;
        } else {
          throw LoginException.authenticationFailed();
        }
      }),
      catchError(error => {
        if (error.status === 401) {
          return throwError(() =>
            LoginException.invalidCredentials() ||
            LoginException.userNotFound() ||
            LoginException.authenticationFailed()
          );
        }
        return throwError(() => error.message || 'Failed to login');
      })
    );
  }

  
  isLoggedIn(): boolean {
    const token = localStorage.getItem("auth-token");

    if (token && !this.jwtService.isTokenExpired(token)) {
      const role: Role = this.jwtService.extractRole(token);
      const user = this.jwtService.extractUser(token);
      this.store.dispatch(setRole({ role }));
      this.store.dispatch(setUser({ user }));
      return true;
    } else {
      this.logout()
      return false;
    }
  }

  logout() {
    localStorage.removeItem("auth-token")
    this.store.dispatch(logout());
  }

  registerAdmin(registerRequest: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register/admin`, registerRequest).pipe(
      catchError((error) => {
        if (error.status === 409) {
          return throwError(() => new Error('Registration failed. Admin already exists.'));
        }
        return throwError(() => error.message || 'Failed to register admin');
      })
    );
  }

  registerTechnician(registerRequest: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register/technician`, registerRequest).pipe(
      catchError((error) => {
        if (error.status === 409) {
          return throwError(() => new Error('Registration failed. Technician already exists.'));
        }
        return throwError(() => error.message || 'Failed to register technician');
      })
    );
  }

  registerClient(registerRequest: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register/client`, registerRequest).pipe(
      catchError((error) => {
        if (error.status === 409) {
          return throwError(() => new Error('Registration failed. Client already exists.'));
        }
        return throwError(() => error.message || 'Failed to register client');
      })
    );
  }

}
