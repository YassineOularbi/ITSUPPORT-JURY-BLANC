import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ShowOnPathDirective } from '../../../directives/show-on-path.directive';
import { RouterLink, RouterModule } from '@angular/router';

@Component({
  selector: 'app-ticket-dynamic-header',
  standalone: true,
  imports: [
    CommonModule, 
    ShowOnPathDirective, 
    RouterLink,
    RouterModule
  ],
  templateUrl: './ticket-dynamic-header.component.html',
  styleUrl: './ticket-dynamic-header.component.css'
})
export class TicketDynamicHeaderComponent {

}
