import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Ticket } from '../models/ticket.model';
import { TicketNotFoundException } from '../exceptions/ticket-not-found.exception';
import { EquipmentNotFoundException } from '../exceptions/equipment-not-found.exception';
import { TechnicianNotFoundException } from '../exceptions/technician-not-found.exception';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class TechnicianService {

  private apiUrl = `${environment.apiUrl}/api/technician`;

  constructor(private http: HttpClient) {}

  getProcessingTickets(id: string): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/get-processing-tickets/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException(`Processing tickets for technician with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to retrieve processing tickets for technician with ID ${id}`);
      })
    );
  }

  getAllTickets(id: string): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/get-all-tickets/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException(`Tickets for technician with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to retrieve tickets for technician with ID ${id}`);
      })
    );
  }

  repairingTicket(id: string): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/repairing-ticket/${id}`, {}).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException(`Ticket with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to update ticket with ID ${id} to 'repairing' status`);
      })
    );
  }

  repairedTicket(id: string): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/repaired-ticket/${id}`, {}).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => 
            new TicketNotFoundException(`Ticket with ID ${id} not found`) ||
            new EquipmentNotFoundException() ||
            new TechnicianNotFoundException()
          );
        }
        return throwError(() => error.message || `Failed to update ticket with ID ${id} to 'repaired' status`);
      })
    );
  }

  failedTicket(id: string): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/failed-ticket/${id}`, {}).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => 
            new TicketNotFoundException(`Ticket with ID ${id} not found`) ||
            new EquipmentNotFoundException() ||
            new TechnicianNotFoundException()
          );
        }
        return throwError(() => error.message || `Failed to update ticket with ID ${id} to 'failed' status`);
      })
    );
  }
}