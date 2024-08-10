import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UserUpdateDto } from '../dtos/user-update-dto.model';
import { ClientNotFoundException } from '../exceptions/client-not-found.exception';
import { Equipment } from '../models/equipment.model';
import { EquipmentNotFoundException } from '../exceptions/equipment-not-found.exception';
import { Breakdown } from '../models/breakdown.model';
import { BreakdownNotFoundException } from '../exceptions/breakdown-not-found.exception';
import { Ticket } from '../models/ticket.model';
import { TicketDto } from '../dtos/ticket-dto.model';
import { TicketNotFoundException } from '../exceptions/ticket-not-found.exception';
import { environment } from '../../../environments/environment.development';


@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl = `${environment.apiUrl}/api/client`;

  constructor(private http: HttpClient) {}

  updateClient(id: string, userUpdateDto: UserUpdateDto): Observable<any> {
    return this.http.put(`${this.apiUrl}/update-client/${id}`, userUpdateDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new ClientNotFoundException(`Client with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to update client with ID ${id}`);
      })
    );
  }

  getEquipmentsByClient(id: string): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.apiUrl}/get-equipments-by-client/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException(`Equipment for client with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to retrieve equipment for client with ID ${id}`);
      })
    );
  }

  getAllBreakdowns(): Observable<Breakdown[]> {
    return this.http.get<Breakdown[]>(`${this.apiUrl}/get-all-breakdowns`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new BreakdownNotFoundException());
        }
        return throwError(() => error.message || 'Failed to retrieve breakdowns');
      })
    );
  }

  reportBreakdownWithTicket(equipmentId: string, breakdownId: string, ticketDto: TicketDto): Observable<Ticket> {
    return this.http.post<Ticket>(
      `${this.apiUrl}/report-breakdown-ticket/${equipmentId}&${breakdownId}`,
      ticketDto
    ).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => 
            new EquipmentNotFoundException(`Equipment with ID ${equipmentId} not found`) ||
            new BreakdownNotFoundException(`Breakdown with ID ${breakdownId} not found`) ||
            new ClientNotFoundException()
          );
        }
        return throwError(() => error.message || 'Failed to report breakdown and create ticket');
      })
    );
  }

  getAllTickets(id: string): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/get-all-tickets/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException(`Tickets for client with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to retrieve tickets for client with ID ${id}`);
      })
    );
  }
}