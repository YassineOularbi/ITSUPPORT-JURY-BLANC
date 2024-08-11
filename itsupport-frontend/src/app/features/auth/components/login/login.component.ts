import { Component, Signal } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../../../core/services/auth.service';
import { LoginRequest } from '../../../../core/interfaces/login-request.interface';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { Role } from '../../../../core/enums/role.enum';
import { AppState } from '../../../../core/ngrx/app.state';
import { Store } from '@ngrx/store';
import { pipe } from 'rxjs';
import { selectRole } from '../../../../core/ngrx/auth.selectors';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private store: Store<AppState>
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const loginRequest: LoginRequest = this.loginForm.value;

      this.authService.login(loginRequest).subscribe({
        next: () => {
          if (this.authService.isLoggedIn()) {
            const role: Signal<Role | null> = this.store.selectSignal(pipe(selectRole));
            const roleValue = role();
            console.log('Role Value:', roleValue);

            if (roleValue !== null) {
              switch (roleValue) {
                case Role.ADMIN:
                  this.router.navigate(['/admin']);
                  break;
                case Role.TECHNICIAN:
                  this.router.navigate(['/technician']);
                  break;
                case Role.CLIENT:
                  this.router.navigate(['/client']);
                  break;
                default:
                  console.log('No matching role found');
                  break;
              }
            } else {
              console.log('Role is null or undefined');
            }
          } else {
            console.log("User is not logged in");
          }
        },
        error: (error) => {
          console.error('Login error', error);
        }
      });
    }
  }  
}
