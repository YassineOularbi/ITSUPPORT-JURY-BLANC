import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDto } from '../../../core/dtos/user-dto.model';
import { Store } from '@ngrx/store';
import { AppState } from '../../../core/ngrx/app.state';
import { selectUser } from '../../../core/ngrx/auth.selectors';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-technician',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './technician.component.html',
  styleUrl: './technician.component.css'
})
export class TechnicianComponent implements OnInit{
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
