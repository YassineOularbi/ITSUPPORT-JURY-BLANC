import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Technician } from '../../../../core/models/technician.model';
import { TechnicianService } from '../../../../core/services/technician.service';
import { TicketService } from '../../../../core/services/ticket.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-assign-ticket-popup',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './assign-ticket-popup.component.html',
  styleUrl: './assign-ticket-popup.component.css'
})
export class AssignTicketPopupComponent {
  @Input() ticketId: number | null = null;
  @Output() close = new EventEmitter<void>();
  technicians: Technician[] = [];
  selectedTechnicianId: number | null = null;

  constructor(
    private technicianService: TechnicianService,
    private ticketService: TicketService,
    private router: Router
  ) {}

  
  getImageUrl(imageName: string | undefined): string {
    return imageName ? `http://localhost:8080/images/${imageName}` : 'https://th.bing.com/th/id/OIP.P5ZiKujilpP0W1rBHWMa5AAAAA?rs=1&pid=ImgDetMain';
  }

  ngOnInit(): void {
    this.technicianService.getAvailableTechnicians().subscribe({
      next: (technicians) => {
        this.technicians = technicians;
      },
      error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
    });
  }

  closePopup(): void {
    this.close.emit();
  }

  onTechnicianSelect(technicianId: number): void {
    if (this.selectedTechnicianId === technicianId) {
      this.selectedTechnicianId = null;
    } else {
      this.selectedTechnicianId = technicianId;
    }
    
  }

  assignTicket(): void {
    if (this.selectedTechnicianId && this.ticketId) {
      this.ticketService.assignTicketToTechnician(this.ticketId.toString(), this.selectedTechnicianId.toString()).subscribe({
        next: ()=>{
          this.router.navigateByUrl('/dashboard/tickets');
          this.closePopup();
        },
        error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
      });
    }
  }

}
