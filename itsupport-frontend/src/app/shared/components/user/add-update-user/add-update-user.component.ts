import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HideShowPasswordDirective } from '../../../directives/hide-show-password.directive';
import { ErrorHighlightDirective } from '../../../directives/error-highlight.directive';
import { PasswordStrengthPipe } from '../../../pipes/password-strength.pipe';
import { RegisterRequest } from '../../../../core/interfaces/register-request.interface';
import { AuthService } from '../../../../core/services/auth.service';
import { passwordStrengthValidator } from '../../../validators/password-strength.validator';
import { AdminService } from '../../../../core/services/admin.service';
import { ClientService } from '../../../../core/services/client.service';
import { TechnicianService } from '../../../../core/services/technician.service';

@Component({
  selector: 'app-add-update-user',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RouterLink,
    HideShowPasswordDirective,
    ErrorHighlightDirective
  ],
  providers: [PasswordStrengthPipe],
  templateUrl: './add-update-user.component.html',
  styleUrls: ['./add-update-user.component.css']
})
export class AddUpdateUserComponent implements OnInit {
  signupForm: FormGroup;
  passwordStrength: { strength: string, score: number } | undefined;
  signupError: string | null = null;
  imagePreviewUrl: string | ArrayBuffer | null = null;
  userType: string | null = null;
  isEditMode: boolean = false;

  @ViewChild('passwordInput') passwordInput!: ElementRef<HTMLInputElement>;
  @ViewChild('signupContainer') signupContainer!: ElementRef;
  @ViewChild('fileInput') fileInput!: ElementRef<HTMLInputElement>;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private adminService: AdminService,
    private clientService: ClientService,
    private technicianService: TechnicianService,
    private router: Router,
    private passwordStrengthPipe: PasswordStrengthPipe,
    private route: ActivatedRoute
  ) {
    this.signupForm = this.fb.group({
      id: [null],
      fullName: ['', Validators.required],
      mail: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, passwordStrengthValidator()]],
      address: ['', Validators.required],
      phone: ['', Validators.required]
    });
  }

  ngOnInit() {
    console.log("khdamaaaaa");
    
    this.setupErrorReset();
    this.route.params.subscribe(params => {
      this.userType = params['type'];
      const userId = params['id'];
      if (userId) {
        this.isEditMode = true;
        this.loadUserData(userId)
      }
    });
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

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.signupForm.patchValue({ picture: file });

      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imagePreviewUrl = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }

  loadUserData(userId: string) {
    switch (this.userType) {
      case 'admin':
        this.adminService.getAdminById(userId).subscribe({
          next: (user) => {
            this.signupForm.patchValue(user);
            this.imagePreviewUrl = user.avatarUrl ? `http://localhost:8080/images/${user.avatarUrl}` : null;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        })
        break;
      case 'client':
        this.clientService.getClientById(userId).subscribe({
          next: (user) => {
            this.signupForm.patchValue(user);
            this.imagePreviewUrl = user.avatarUrl ? user.avatarUrl : null;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        })
        break;
      case 'technician':
        this.technicianService.getTechnicianById(userId).subscribe({
          next: (user) => {
            this.signupForm.patchValue(user);
            this.imagePreviewUrl = user.avatarUrl ? user.avatarUrl : null;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        })
        break;
      default:
        this.router.navigate(['/dashboard/error'], { queryParams: { message: 'Unknown user type' } });
        return;
    }

  }

  onSubmit() {
    if (this.signupForm.valid) {
      const formData = new FormData();

      formData.append('fullName', this.signupForm.get('fullName')?.value);
      formData.append('mail', this.signupForm.get('mail')?.value);
      formData.append('username', this.signupForm.get('username')?.value);
      formData.append('password', this.signupForm.get('password')?.value);
      formData.append('address', this.signupForm.get('address')?.value);
      formData.append('phone', this.signupForm.get('phone')?.value);

      if (this.fileInput.nativeElement.files && this.fileInput.nativeElement.files.length > 0) {
        formData.append('picture', this.fileInput.nativeElement.files[0]);
      }

      const userId = this.signupForm.get('id')?.value;
      if (this.isEditMode && userId) {
        switch (this.userType) {
          case 'admin':
            this.adminService.updateAdmin(userId, formData).subscribe({
              next: () => {
                this.signupContainer.nativeElement.classList.add('fade-out');

                setTimeout(() => {
                  this.router.navigateByUrl(`/dashboard/users/${this.userType}`);
                }, 1700);
              },
              error: (error) => {
                console.error('Update error', error);
                this.signupError = error.message;
              }
            });
            break;
          case 'client':
            this.clientService.updateClient(userId, formData).subscribe({
              next: () => {
                this.signupContainer.nativeElement.classList.add('fade-out');

                setTimeout(() => {
                  this.router.navigateByUrl(`/dashboard/users/${this.userType}`);
                }, 1700);
              },
              error: (error) => {
                console.error('Update error', error);
                this.signupError = error.message;
              }
            });
            break;
          case 'technician':
            this.technicianService.updateTechnician(userId, formData).subscribe({
              next: () => {
                this.signupContainer.nativeElement.classList.add('fade-out');

                setTimeout(() => {
                  this.router.navigateByUrl(`/dashboard/users/${this.userType}`);
                }, 1700);
              },
              error: (error) => {
                console.error('Update error', error);
                this.signupError = error.message;
              }
            });
        }

      } else {
        switch (this.userType) {
          case 'admin':
            this.authService.registerAdmin(formData).subscribe({
              next: () => {
                this.signupContainer.nativeElement.classList.add('fade-out');

                setTimeout(() => {
                  this.router.navigateByUrl('/dashboard/users/admin');
                }, 1700);
              },
              error: (error) => {
                console.error('Signup error', error);
                this.signupError = error.message;
              }
            });
            break;
          case 'client':
            this.authService.registerClient(formData).subscribe({
              next: () => {
                this.signupContainer.nativeElement.classList.add('fade-out');

                setTimeout(() => {
                  this.router.navigateByUrl('/dashboard/users/client');
                }, 1700);
              },
              error: (error) => {
                console.error('Signup error', error);
                this.signupError = error.message;
              }
            });
            break;
          case 'technician':
            this.authService.registerTechnician(formData).subscribe({
              next: () => {
                this.signupContainer.nativeElement.classList.add('fade-out');

                setTimeout(() => {
                  this.router.navigateByUrl('/dashboard/users/technician');
                }, 1700);
              },
              error: (error) => {
                console.error('Signup error', error);
                this.signupError = error.message;
              }
            });
            break;
          default:
            setTimeout(() => {
              this.router.navigateByUrl('/dashboard/users');
            }, 1700);
            break;
        }
      }
    }
  }
}
