import { Component, OnInit } from '@angular/core';
import { UserDto } from '../../../core/dtos/user-dto.model';
import { Observable } from 'rxjs';
import { AppState } from '../../../core/ngrx/app.state';
import { Store } from '@ngrx/store';
import { selectUser } from '../../../core/ngrx/auth.selectors';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './client.component.html',
  styleUrl: './client.component.css'
})
export class ClientComponent implements OnInit{
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
