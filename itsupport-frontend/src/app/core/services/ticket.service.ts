import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Ticket } from '../models/ticket.model';
import { environment } from '../../../environments/environment.development';
import { EquipmentNotFoundException } from '../exceptions/equipment-not-found.exception';
import { BreakdownNotFoundException } from '../exceptions/breakdown-not-found.exception';
import { ClientNotFoundException } from '../exceptions/client-not-found.exception';
import { TicketNotFoundException } from '../exceptions/ticket-not-found.exception';
import { TechnicianNotFoundException } from '../exceptions/technician-not-found.exception';
import { TicketDto } from '../dtos/ticket-dto.model';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private apiUrl = `${environment.apiUrl}/api/ticket`;

  constructor(private http: HttpClient) {}

  reportBreakdownWithTicket(equipmentId: string | undefined, breakdownId: string | undefined, ticketDto: TicketDto): Observable<Ticket> {
    return this.http.post<Ticket>(`${this.apiUrl}/client/report-breakdown-ticket/${equipmentId}&${breakdownId}`, ticketDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          if (error.message.includes('equipment')) {
            return throwError(() => new EquipmentNotFoundException());
          } else if (error.message.includes('breakdown')) {
            return throwError(() => new BreakdownNotFoundException());
          } else if (error.message.includes('client')) {
            return throwError(() => new ClientNotFoundException());
          }
        }
        return throwError(() => error.message || `Failed to report breakdown with ticket`);
      })
    );
  }

  getAllTicketsByClient(clientId: string | undefined): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/client/get-all-tickets/${clientId}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve tickets for client with ID ${clientId}`);
      })
    );
  }

  assignTicketToTechnician(ticketId: string, technicianId: string): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/admin/assign-ticket/${ticketId}/to/${technicianId}`, {}).pipe(
      catchError((error) => {
        if (error.status === 404) {
          if (error.message.includes('ticket')) {
            return throwError(() => new TicketNotFoundException());
          } else if (error.message.includes('technician')) {
            return throwError(() => new TechnicianNotFoundException());
          }
        }
        return throwError(() => error.message || `Failed to assign ticket with ID ${ticketId} to technician with ID ${technicianId}`);
      })
    );
  }

  getAllTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/admin/get-all-tickets`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve all tickets`);
      })
    );
  }

  getPendingTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/admin/get-pending-tickets`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve pending tickets`);
      })
    );
  }

  getProcessingTickets(technicianId: string): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/technician/get-processing-tickets/${technicianId}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve processing tickets for technician with ID ${technicianId}`);
      })
    );
  }

  getAllTicketsByTechnician(technicianId: string | undefined): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/technician/get-all-tickets/${technicianId}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve all tickets for technician with ID ${technicianId}`);
      })
    );
  }

  repairingTicket(ticketId: string): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/technician/repairing-ticket/${ticketId}`, {}).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException());
        }
        return throwError(() => error.message || `Failed to update ticket status to 'repairing' for ticket with ID ${ticketId}`);
      })
    );
  }

  repairedTicket(ticketId: string): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/technician/repaired-ticket/${ticketId}`, {}).pipe(
      catchError((error) => {
        if (error.status === 404) {
          if (error.message.includes('ticket')) {
            return throwError(() => new TicketNotFoundException());
          } else if (error.message.includes('equipment')) {
            return throwError(() => new EquipmentNotFoundException());
          } else if (error.message.includes('technician')) {
            return throwError(() => new TechnicianNotFoundException());
          }
        }
        return throwError(() => error.message || `Failed to update ticket status to 'repaired' for ticket with ID ${ticketId}`);
      })
    );
  }

  failedTicket(ticketId: string): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/technician/failed-ticket/${ticketId}`, {}).pipe(
      catchError((error) => {
        if (error.status === 404) {
          if (error.message.includes('ticket')) {
            return throwError(() => new TicketNotFoundException());
          } else if (error.message.includes('equipment')) {
            return throwError(() => new EquipmentNotFoundException());
          } else if (error.message.includes('technician')) {
            return throwError(() => new TechnicianNotFoundException());
          }
        }
        return throwError(() => error.message || `Failed to update ticket status to 'failed' for ticket with ID ${ticketId}`);
      })
    );
  }
}
