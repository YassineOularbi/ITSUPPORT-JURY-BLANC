import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Equipment } from '../models/equipment.model';
import { environment } from '../../../environments/environment.development';
import { EquipmentNotFoundException } from '../exceptions/equipment-not-found.exception';
import { ClientNotFoundException } from '../exceptions/client-not-found.exception';
import { EquipmentDto } from '../dtos/equipment-dto.model';

@Injectable({
  providedIn: 'root'
})
export class EquipmentService {
  private apiUrl = `${environment.apiUrl}/api/equipment`;

  constructor(private http: HttpClient) {}

  createEquipment(equipmentDto: FormData): Observable<Equipment> {
    return this.http.post<Equipment>(`${this.apiUrl}/admin/create-equipment`, equipmentDto).pipe(
      catchError((error) => {
        if (error.status === 400) {
          return throwError(() => error.message || `Failed to create equipment`);
        }
        return throwError(() => error.message || `Failed to create equipment`);
      })
    );
  }

  getAllEquipments(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.apiUrl}/admin/get-all-equipments`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve all equipments`);
      })
    );
  }

  getAvailableEquipments(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.apiUrl}/admin/get-available-equipments`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve all equipments`);
      })
    );
  }

  getInServiceEquipments(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.apiUrl}/admin/get-in-service-equipments`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve all equipments`);
      })
    );
  }

  getBrokenDownEquipments(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.apiUrl}/admin/get-broken-down-equipments`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve all equipments`);
      })
    );
  }

  getAllEquipmentOutService(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.apiUrl}/admin/get-all-equipment-out-service`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve out-of-service equipment`);
      })
    );
  }

  getEquipmentById(id: string): Observable<Equipment> {
    return this.http.get<Equipment>(`${this.apiUrl}/admin/get-equipment-by-id/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve equipment with ID ${id}`);
      })
    );
  }

  updateEquipment(id: string, equipmentDto: FormData): Observable<Equipment> {
    return this.http.put<Equipment>(`${this.apiUrl}/admin/update-equipment/${id}`, equipmentDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException());
        }
        return throwError(() => error.message || `Failed to update equipment with ID ${id}`);
      })
    );
  }

  deleteEquipment(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/admin/delete-equipment/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException());
        }
        return throwError(() => error.message || `Failed to delete equipment with ID ${id}`);
      })
    );
  }

  assignEquipmentToClient(equipmentId: string, clientId: string): Observable<Equipment> {
    return this.http.put<Equipment>(`${this.apiUrl}/admin/assign-equipment/${equipmentId}/to/${clientId}`, {}).pipe(
      catchError((error) => {
        if (error.status === 404) {
          if (error.message.includes('equipment')) {
            return throwError(() => new EquipmentNotFoundException());
          } else if (error.message.includes('client')) {
            return throwError(() => new ClientNotFoundException());
          }
        }
        return throwError(() => error.message || `Failed to assign equipment with ID ${equipmentId} to client with ID ${clientId}`);
      })
    );
  }

  getEquipmentsByClient(id: string | undefined): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.apiUrl}/client/get-equipments-by-client/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new EquipmentNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve equipment for client with ID ${id}`);
      })
    );
  }
}
