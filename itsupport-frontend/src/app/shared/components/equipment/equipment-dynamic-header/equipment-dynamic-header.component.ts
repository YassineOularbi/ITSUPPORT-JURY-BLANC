import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ShowOnPathDirective } from '../../../directives/show-on-path.directive';
import { RouterLink, RouterModule } from '@angular/router';
import { Observable } from 'rxjs';
import { Role } from '../../../../core/enums/role.enum';
import { AppState } from '../../../../core/ngrx/app.state';
import { Store } from '@ngrx/store';
import { selectRole } from '../../../../core/ngrx/auth.selectors';

@Component({
  selector: 'app-equipment-dynamic-header',
  standalone: true,
  imports: [
    CommonModule, 
    ShowOnPathDirective, 
    RouterLink,
    RouterModule
  ],
  templateUrl: './equipment-dynamic-header.component.html',
  styleUrl: './equipment-dynamic-header.component.css'
})
export class EquipmentDynamicHeaderComponent {
  role$: Observable<Role | null>; 
  Role = Role;

  constructor(
    private store: Store<AppState> 
  ) {
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
}
