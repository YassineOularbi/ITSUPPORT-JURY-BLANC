import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../../core/services/auth.service';
import { Router, RouterLink } from '@angular/router';
import { RegisterRequest } from '../../../../core/interfaces/register-request.interface';
import { PasswordStrengthPipe } from '../../../../shared/pipes/password-strength.pipe';
import { CommonModule } from '@angular/common';
import { HideShowPasswordDirective } from '../../../../shared/directives/hide-show-password.directive';
import { passwordStrengthValidator } from '../../../../shared/validators/password-strength.validator';
import { ErrorHighlightDirective } from '../../../../shared/directives/error-highlight.directive';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RouterLink,
    HideShowPasswordDirective,
    ErrorHighlightDirective
  ],
  providers: [PasswordStrengthPipe],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signupForm: FormGroup;
  passwordStrength: { strength: string, score: number } | undefined;
  signupError: string | null = null;
  showSuccessAnimation: boolean = false;

  @ViewChild('passwordInput') passwordInput!: ElementRef<HTMLInputElement>;
  @ViewChild('signupContainer') signupContainer!: ElementRef;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private passwordStrengthPipe: PasswordStrengthPipe,
  ) {
    this.signupForm = this.fb.group({
      fullName: ['', Validators.required],
      mail: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, passwordStrengthValidator()]]
    });
  }

  ngOnInit() {
    this.setupErrorReset();
  }

  setupErrorReset() {
    this.signupForm.valueChanges.subscribe(() => {
      this.signupError = null;
    });
  }

  checkPasswordStrength() {
    if (this.passwordInput) {
      this.passwordStrength = this.passwordStrengthPipe.transform(this.passwordInput.nativeElement.value);
    }
  }

  onSubmit() {
    if (this.signupForm.valid) {
      const registerRequest: RegisterRequest = this.signupForm.value;

      this.authService.registerAdmin(registerRequest).subscribe({
        next: () => {
          this.signupContainer.nativeElement.classList.add('fade-out');
          this.showSuccessAnimation = true;
          setTimeout(() => {
            this.router.navigateByUrl('/login');
          }, 1700);
        },
        error: (error) => {
          console.error('Signup error', error);
          this.signupError = error.message;
        }
      });
    }
  }
}