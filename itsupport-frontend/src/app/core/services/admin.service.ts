import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AdminNotFoundException } from '../exceptions/admin-not-found.exception';
import { ClientNotFoundException } from '../exceptions/client-not-found.exception';
import { TechnicianNotFoundException } from '../exceptions/technician-not-found.exception';
import { EquipmentNotFoundException } from '../exceptions/equipment-not-found.exception';
import { BreakdownNotFoundException } from '../exceptions/breakdown-not-found.exception';
import { TicketNotFoundException } from '../exceptions/ticket-not-found.exception';
import { Admin } from '../models/admin.model';
import { Client } from '../models/client.model';
import { UserUpdateDto } from '../dtos/user-update-dto.model';
import { Technician } from '../models/technician.model';
import { EquipmentDto } from '../dtos/equipment-dto.model';
import { Equipment } from '../models/equipment.model';
import { BreakdownDto } from '../dtos/breakdown-dto.model';
import { Breakdown } from '../models/breakdown.model';
import { Ticket } from '../models/ticket.model';
import { environment } from '../../../environments/environment.development';


@Injectable({
  providedIn: 'root'
})
export class AdminService {
  
  private apiUrl = `${environment.apiUrl}/api/admin`;

  constructor(private http: HttpClient) {}

  getAllAdmins(): Observable<Admin[]> {
    return this.http.get<Admin[]>(`${this.apiUrl}/get-all-admins`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new AdminNotFoundException('Admins not found'));
        }
        return throwError(() => new Error(error.message || 'Failed to retrieve admins'));
      })
    );
  }

  getAdminById(id: number): Observable<Admin> {
    return this.http.get<Admin>(`${this.apiUrl}/get-admin-by-id/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new AdminNotFoundException(`Admin with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to retrieve admin with ID ${id}`);
      })
    );
  }

  updateAdmin(id: number, userUpdateDto: UserUpdateDto): Observable<Admin> {
    return this.http.put<Admin>(`${this.apiUrl}/update-admin/${id}`, userUpdateDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new AdminNotFoundException(`Admin with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to update admin with ID ${id}`);
      })
    );
  }

  deleteAdmin(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-admin/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new AdminNotFoundException(`Admin with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to delete admin with ID ${id}`);
      })
    );
  }

  getAllClients(): Observable<Client[]> {
    return this.http.get<Client[]>(`${this.apiUrl}/get-all-clients`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new ClientNotFoundException('Clients not found'));
        }
        return throwError(() => error.message || 'Failed to retrieve clients');
      })
    );
  }

  getClientById(id: number): Observable<Client> {
    return this.http.get<Client>(`${this.apiUrl}/get-client-by-id/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new ClientNotFoundException(`Client with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to retrieve client with ID ${id}`);
      })
    );
  }

  updateClient(id: number, userUpdateDto: UserUpdateDto): Observable<Client> {
    return this.http.put<Client>(`${this.apiUrl}/update-client/${id}`, userUpdateDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new ClientNotFoundException(`Client with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to update client with ID ${id}`);
      })
    );
  }

  deleteClient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-client/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(new ClientNotFoundException(`Client with ID ${id} not found`));
        }
        return throwError(error.message || `Failed to delete client with ID ${id}`);
      })
    );
  }

  getAllTechnicians(): Observable<Technician[]> {
    return this.http.get<Technician[]>(`${this.apiUrl}/get-all-technicians`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TechnicianNotFoundException('Technicians not found'));
        }
        return throwError(() => error.message || 'Failed to retrieve technicians');
      })
    );
  }

  getAvailableTechnicians(): Observable<Technician[]> {
    return this.http.get<Technician[]>(`${this.apiUrl}/get-available-technicians`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TechnicianNotFoundException('Available technicians not found'));
        }
        return throwError(() => error.message || 'Failed to retrieve available technicians');
      })
    );
  }

  getTechnicianById(id: number): Observable<Technician> {
    return this.http.get<Technician>(`${this.apiUrl}/get-technician-by-id/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TechnicianNotFoundException(`Technician with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to retrieve technician with ID ${id}`);
      })
    );
  }

  updateTechnician(id: number, userUpdateDto: UserUpdateDto): Observable<Technician> {
    return this.http.put<Technician>(`${this.apiUrl}/update-technician/${id}`, userUpdateDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TechnicianNotFoundException(`Technician with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to update technician with ID ${id}`);
      })
    );
  }

  deleteTechnician(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-technician/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TechnicianNotFoundException(`Technician with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to delete technician with ID ${id}`);
      })
    );
  }

  createEquipment(equipmentDto: EquipmentDto): Observable<Equipment> {
    return this.http.post<Equipment>(`${this.apiUrl}/create-equipment`, equipmentDto).pipe(
      catchError((error) => {
        return throwError(() => error.message || 'Failed to create equipment');
      })
    );
  }

  getAllEquipments(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.apiUrl}/get-all-equipments`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException('Equipment not found'));
        }
        return throwError(() => error.message || 'Failed to retrieve equipment');
      })
    );
  }

  getEquipmentById(id: number): Observable<Equipment> {
    return this.http.get<Equipment>(`${this.apiUrl}/get-equipment-by-id/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException(`Equipment with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to retrieve equipment with ID ${id}`);
      })
    );
  }

  updateEquipment(id: number, equipmentDto: EquipmentDto): Observable<Equipment> {
    return this.http.put<Equipment>(`${this.apiUrl}/update-equipment/${id}`, equipmentDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException(`Equipment with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to update equipment with ID ${id}`);
      })
    );
  }

  deleteEquipment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-equipment/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException(`Equipment with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to delete equipment with ID ${id}`);
      })
    );
  }

  assignEquipmentToClient(equipmentId: number, clientId: number): Observable<Equipment> {
    return this.http.put<Equipment>(`${this.apiUrl}/assign-equipment/${equipmentId}/to/${clientId}`, {}).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException(`Equipment with ID ${equipmentId} not found`) || new ClientNotFoundException(`Client with ID ${clientId} not found`));
        }
        return throwError(() => error.message || `Failed to assign equipment with ID ${equipmentId} to client with ID ${clientId}`);
      })
    );
  }

  getAllEquipmentOutService(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.apiUrl}/get-all-equipment-out-service`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException('Equipment not found'));
        }
        return throwError(() => error.message || 'Failed to retrieve equipment out of service');
      })
    );
  }

  createBreakdown(breakdownDto: BreakdownDto): Observable<Breakdown> {
    return this.http.post<Breakdown>(`${this.apiUrl}/create-breakdown`, breakdownDto).pipe(
      catchError((error) => {
        return throwError(() => error.message || 'Failed to create breakdown');
      })
    );
  }

  getAllBreakdowns(): Observable<Breakdown[]> {
    return this.http.get<Breakdown[]>(`${this.apiUrl}/get-all-breakdowns`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new BreakdownNotFoundException('Breakdowns not found'));
        }
        return throwError(() => error.message || 'Failed to retrieve breakdowns');
      })
    );
  }

  getBreakdownById(id: number): Observable<Breakdown> {
    return this.http.get<Breakdown>(`${this.apiUrl}/get-breakdown-by-id/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new BreakdownNotFoundException(`Breakdown with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to retrieve breakdown with ID ${id}`);
      })
    );
  }

  updateBreakdown(id: number, breakdownDto: BreakdownDto): Observable<Breakdown> {
    return this.http.put<Breakdown>(`${this.apiUrl}/update-breakdown/${id}`, breakdownDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new BreakdownNotFoundException(`Breakdown with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to update breakdown with ID ${id}`);
      })
    );
  }

  deleteBreakdown(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-breakdown/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new BreakdownNotFoundException(`Breakdown with ID ${id} not found`));
        }
        return throwError(() => error.message || `Failed to delete breakdown with ID ${id}`);
      })
    );
  }

  assignTicketToTechnician(ticketId: number, technicianId: number): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/assign-ticket/${ticketId}/to/${technicianId}`, {}).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException(`Ticket with ID ${ticketId} not found`) || new TechnicianNotFoundException(`Technician with ID ${technicianId} not found`));
        }
        return throwError(() => error.message || `Failed to assign ticket with ID ${ticketId} to technician with ID ${technicianId}`);
      })
    );
  }

  getAllTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/get-all-tickets`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException('Tickets not found'));
        }
        return throwError(() => error.message || 'Failed to retrieve tickets');
      })
    );
  }

  getPendingTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/get-pending-tickets`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TicketNotFoundException('Pending tickets not found'));
        }
        return throwError(() => error.message || 'Failed to retrieve pending tickets');
      })
    );
  }
}