import { Component, ElementRef, EventEmitter, Output, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDto } from '../../../core/dtos/user-dto.model';
import { Store } from '@ngrx/store';
import { AppState } from '../../../core/ngrx/app.state';
import { selectRole, selectUser } from '../../../core/ngrx/auth.selectors';
import { CommonModule } from '@angular/common';
import { Role } from '../../../core/enums/role.enum';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  user$: Observable<UserDto | null>;
  role$: Observable<Role | null>;
  Role = Role;
  @Output() sidebarToggle = new EventEmitter<boolean>();

  @ViewChild('sidebar', { static: true }) sidebar!: ElementRef;

  isOpen = true;

  constructor(private store: Store<AppState>, private authService: AuthService, private route: Router) {
    this.user$ = this.store.select(selectUser);
    this.role$ = this.store.select(selectRole);
  }

  toggleSidebar() {
    this.isOpen = !this.isOpen;
    const sidebarElement = this.sidebar.nativeElement;
    const logoElement = sidebarElement.querySelector('.logo_name') as HTMLElement;
    const closeBtn = sidebarElement.querySelector('#btn') as HTMLElement;
    sidebarElement.classList.toggle('open');
    logoElement.classList.toggle('hidden-logo');
    if (sidebarElement.classList.contains('open')) {
      closeBtn.classList.replace('bx-menu', 'bx-menu-alt-right');
    } else {
      closeBtn.classList.replace('bx-menu-alt-right', 'bx-menu');
    }
    this.sidebarToggle.emit(this.isOpen);
  }

  hasRole(roles: Role[]): boolean {
    let hasRole = false;
    this.role$.subscribe(role => {
      if (role) {
        hasRole = roles.includes(role);
      }
    }).unsubscribe();
    return hasRole;
  }

  onLogout(): void {
    this.authService.logout();
    this.route.navigateByUrl('login');
  }

  ngOnInit(): void {
    this.user$.subscribe();
    this.role$.subscribe();
  }
}
