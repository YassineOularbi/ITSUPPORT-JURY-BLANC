import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { TicketService } from '../../../../core/services/ticket.service';
import { Router } from '@angular/router';
import { Ticket } from '../../../../core/models/ticket.model';
import { CommonModule } from '@angular/common';
import { TicketStatus } from '../../../../core/enums/ticket-status.enum';
import { Role } from '../../../../core/enums/role.enum';
import { UserDto } from '../../../../core/dtos/user-dto.model';
import { Observable } from 'rxjs';
import { Store } from '@ngrx/store';
import { AppState } from '../../../../core/ngrx/app.state';
import { selectRole, selectUser } from '../../../../core/ngrx/auth.selectors';
import { AssignTicketPopupComponent } from '../assign-ticket-popup/assign-ticket-popup.component';

@Component({
  selector: 'app-ticket-list',
  standalone: true,
  imports: [
    CommonModule,
    AssignTicketPopupComponent
  ],
  templateUrl: './ticket-list.component.html',
  styleUrl: './ticket-list.component.css'
})
export class TicketListComponent implements OnInit{
  tickets: Ticket[] = [];
  TicketStatus = TicketStatus;
  role$: Observable<Role | null>; 
  user$: Observable<UserDto | null>;
  Role = Role;
  isPopupOpen = false;
  selectedTicketId: number | null = null;

  @Output() assignTicket = new EventEmitter<number>();

  constructor(private ticketService: TicketService, private router: Router, private store: Store<AppState>) {
    this.role$ = this.store.select(selectRole);
    this.user$ = this.store.select(selectUser)
  }

  ngOnInit(): void {
    this.role$.subscribe(userRole => {
      if (userRole === Role.ADMIN) {
        this.ticketService.getAllTickets().subscribe({
          next: (response: Ticket[]) => {
            this.tickets = response;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
    
        })
      } else if (userRole === Role.CLIENT){
        this.user$.subscribe(
          user => {
            this.ticketService.getAllTicketsByClient(user?.id?.toString()).subscribe({
              next: (response: Ticket[]) => {
                this.tickets = response;
              },
              error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        
            })
          }
        )
      } else if (userRole === Role.TECHNICIAN){
        this.user$.subscribe(
          user => {
            this.ticketService.getAllTicketsByTechnician(user?.id?.toString()).subscribe({
              next: (response: Ticket[]) => {
                this.tickets = response;
              },
              error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        
            })
          }
        )
      }
    })
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

  openAssignPopup(ticketId: number): void {
    this.selectedTicketId = ticketId;
    this.isPopupOpen = true;
  }

  closeAssignPopup(): void {
    this.isPopupOpen = false;
    this.selectedTicketId = null;
  }

  onRepairing(ticketId: number): void {
    this.ticketService.repairingTicket(ticketId.toString()).subscribe({
      next: (response: Ticket) => {
        this.ngOnInit()
        this.router.navigate(['/dashboard/tickets'])
      },
      error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
    })
  }

  onRepaired(ticketId: number): void {
    this.ticketService.repairedTicket(ticketId.toString()).subscribe({
      next: (response: Ticket) => {
        this.ngOnInit()
        this.router.navigate(['/dashboard/tickets'])
      },
      error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
    })
  }

  onFailed(ticketId: number): void {
    this.ticketService.failedTicket(ticketId.toString()).subscribe({
      next: (response: Ticket) => {
        this.ngOnInit()
        this.router.navigate(['/dashboard/tickets'])
      },
      error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
    })
  }
}
