import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { AppState } from '../../../core/ngrx/app.state';
import { Store } from '@ngrx/store';
import { selectRole, selectUser } from '../../../core/ngrx/auth.selectors';
import { Role } from '../../../core/enums/role.enum';
import { filter, map, Observable, Subscription } from 'rxjs';
import { UserDto } from '../../../core/dtos/user-dto.model';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { BreadcrumbPipe } from '../../pipes/breadcrumb.pipe';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    CommonModule,
    BreadcrumbPipe 
  ],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  @Input() isSidebarOpen: boolean = true;
  user$: Observable<UserDto | null>;
  role$: Observable<Role | null>;
  Role = Role;
  currentPath: string = '';

  constructor(private store: Store<AppState>, private router: Router) {
    this.user$ = this.store.select(selectUser);
    this.role$ = this.store.select(selectRole);
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

  ngOnInit(): void {
    this.user$.subscribe();
    this.role$.subscribe();
    this.currentPath = this.router.url;

  }

}
