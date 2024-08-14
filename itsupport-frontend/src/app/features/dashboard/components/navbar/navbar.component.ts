import { CommonModule } from '@angular/common';
import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { AppState } from '../../../../core/ngrx/app.state';
import { Store } from '@ngrx/store';
import { selectRole, selectUser } from '../../../../core/ngrx/auth.selectors';
import { Role } from '../../../../core/enums/role.enum';
import { Observable, Subscription } from 'rxjs';
import { UserDto } from '../../../../core/dtos/user-dto.model';
import { Router, NavigationEnd } from '@angular/router';
import { BreadcrumbPipe } from '../../../../shared/pipes/breadcrumb.pipe';

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
export class NavbarComponent implements OnInit, OnDestroy {
  @Input() isSidebarOpen: boolean = true;
  user$: Observable<UserDto | null>;
  role$: Observable<Role | null>;
  Role = Role;
  currentPath: string = 'Dashboard';
  private routerSubscription: Subscription = new Subscription();

  constructor(private store: Store<AppState>, private router: Router) {
    this.user$ = this.store.select(selectUser);
    this.role$ = this.store.select(selectRole);
  }

  getImageUrl(imageName: string | undefined): string {
    return imageName ? `http://localhost:8080/images/${imageName}` : 'https://th.bing.com/th/id/OIP.P5ZiKujilpP0W1rBHWMa5AAAAA?rs=1&pid=ImgDetMain';
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
    this.routerSubscription = this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentPath = event.urlAfterRedirects;
      }
    });
    this.user$.subscribe();
    this.role$.subscribe();
  }

  ngOnDestroy(): void {
    if (this.routerSubscription) {
      this.routerSubscription.unsubscribe();
    }
  }
}
