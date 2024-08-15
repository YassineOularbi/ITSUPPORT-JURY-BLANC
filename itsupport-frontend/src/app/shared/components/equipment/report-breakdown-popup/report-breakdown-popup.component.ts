import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Breakdown } from '../../../../core/models/breakdown.model';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TicketService } from '../../../../core/services/ticket.service';
import { TicketDto } from '../../../../core/dtos/ticket-dto.model';
import { Ticket } from '../../../../core/models/ticket.model';
import { CommonModule } from '@angular/common';
import { BreakdownService } from '../../../../core/services/breakdown.service';
import { Equipment } from '../../../../core/models/equipment.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-report-breakdown-popup',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './report-breakdown-popup.component.html',
  styleUrls: ['./report-breakdown-popup.component.css']
})
export class ReportBreakdownPopupComponent {
  @Input() equipment: Equipment | null = null;
  @Input() isPopupOpen = false;
  @Output() close = new EventEmitter<void>();
  breakdowns: Breakdown[] = [];
  selectedBreakdownId: number | null = null;
  breakdownForm!: FormGroup;

  constructor(
    private fb: FormBuilder, 
    private ticketService: TicketService, 
    private breakdownService: BreakdownService, 
    private router: Router
  ) {}

  ngOnInit(): void {
    this.breakdownForm = this.fb.group({
      description: ['', Validators.required]
    });

    this.breakdownService.getAllBreakdowns().subscribe({
      next: (breakdowns) => {
        this.breakdowns = breakdowns;
      },
      error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
    });
  }

  onSubmit(): void {
    if (this.breakdownForm.valid && this.selectedBreakdownId) {
      const ticketDto: TicketDto = this.breakdownForm.value;

      this.ticketService.reportBreakdownWithTicket(this.equipment?.id?.toString(), this.selectedBreakdownId.toString(), ticketDto).subscribe({
        next: (ticket: Ticket) => {
          console.log('Breakdown reported successfully:', ticket);
          this.router.navigate(["/dashboard/equipments"]);
          this.closePopup();
        },
        error: (error) => {
          console.error('Failed to report breakdown:', error);
        }
      });
    }
  }

  onBreakdownChange(event: Event): void {
    const target = event.target as HTMLSelectElement;
    this.selectedBreakdownId = Number(target.value);
  }

  closePopup(): void {
    this.isPopupOpen = false;
    this.close.emit();
  }
}
