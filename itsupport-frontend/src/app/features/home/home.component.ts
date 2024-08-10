import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDto } from '../../core/dtos/user-dto.model';
import { Store } from '@ngrx/store';
import { AppState } from '../../core/ngrx/app.state';
import { selectUser } from '../../core/ngrx/auth.selectors';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
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
