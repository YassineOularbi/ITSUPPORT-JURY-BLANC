import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
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
import { HideShowPasswordDirective } from '../../../../shared/directives/hide-show-password.directive';
import { ErrorHighlightDirective } from '../../../../shared/directives/error-highlight.directive';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RouterLink,
    HideShowPasswordDirective,
    ErrorHighlightDirective
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginError: string | null = null;
  showSuccessAnimation: boolean = false;

  @ViewChild('loginContainer') loginContainer!: ElementRef;

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

  ngOnInit() {
    this.setupErrorReset();
  }

  setupErrorReset() {
    this.loginForm.get('username')?.valueChanges.subscribe(() => {
      this.loginError = null;
    });
    this.loginForm.get('password')?.valueChanges.subscribe(() => {
      this.loginError = null;
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const loginRequest: LoginRequest = this.loginForm.value;

      this.authService.login(loginRequest).subscribe({
        next: () => {
          if (this.authService.isLoggedIn()) {
            this.loginContainer.nativeElement.classList.add('fade-out');
            this.showSuccessAnimation = true;
            setTimeout(() => {
              this.store.select(pipe(selectRole)).subscribe(role => {
                if (role !== null) {
                  switch (role) {
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
              });
            }, 1700);
          }
        },
        error: (error) => {
          console.error('Login error', error);
          this.loginError = error.message;
        }
      });
    }
  }
}