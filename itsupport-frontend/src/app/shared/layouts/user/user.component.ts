import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UserDynamicHeaderComponent } from '../../components/user/user-dynamic-header/user-dynamic-header.component';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [
    RouterOutlet,
    UserDynamicHeaderComponent
  ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit{
  ngOnInit(): void {
    console.log('UserComponent loaded');
  }
}
