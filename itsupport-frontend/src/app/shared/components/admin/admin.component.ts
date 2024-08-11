import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UserDto } from '../../../core/dtos/user-dto.model';
import { Store } from '@ngrx/store';
import { AppState } from '../../../core/ngrx/app.state';
import { Observable } from 'rxjs';
import { selectUser } from '../../../core/ngrx/auth.selectors';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  loading = false;
  user$: Observable<UserDto | null>;

  constructor(private store: Store<AppState>) {
    this.user$ = this.store.select(selectUser);
  }

  ngOnInit(): void {
    this.loading = true
    this.user$.subscribe();
  }
}
