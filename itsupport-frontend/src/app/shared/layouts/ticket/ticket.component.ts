import { Component } from '@angular/core';
import { TicketDynamicHeaderComponent } from "../../components/ticket/ticket-dynamic-header/ticket-dynamic-header.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-ticket',
  standalone: true,
  imports: [
    TicketDynamicHeaderComponent,
    RouterOutlet
  ],
  templateUrl: './ticket.component.html',
  styleUrl: './ticket.component.css'
})
export class TicketComponent {

}
