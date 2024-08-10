import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Technician } from '../models/technician.model';
import { environment } from '../../../environments/environment.development';
import { TechnicianNotFoundException } from '../exceptions/technician-not-found.exception';
import { UserUpdateDto } from '../dtos/user-update-dto.model';

@Injectable({
  providedIn: 'root'
})
export class TechnicianService {
  private apiUrl = `${environment.apiUrl}/api/technician`;

  constructor(private http: HttpClient) {}

  getAllTechnicians(): Observable<Technician[]> {
    return this.http.get<Technician[]>(`${this.apiUrl}/get-all-technicians`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TechnicianNotFoundException());
        }
        return throwError(() => error.message || 'Failed to retrieve all technicians');
      })
    );
  }

  getAvailableTechnicians(): Observable<Technician[]> {
    return this.http.get<Technician[]>(`${this.apiUrl}/get-available-technicians`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TechnicianNotFoundException());
        }
        return throwError(() => error.message || 'Failed to retrieve available technicians');
      })
    );
  }

  getTechnicianById(id: string): Observable<Technician> {
    return this.http.get<Technician>(`${this.apiUrl}/get-technician-by-id/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TechnicianNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve technician with ID ${id}`);
      })
    );
  }

  updateTechnician(id: string, userUpdateDto: UserUpdateDto): Observable<Technician> {
    return this.http.put<Technician>(`${this.apiUrl}/update-technician/${id}`, userUpdateDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TechnicianNotFoundException());
        }
        return throwError(() => error.message || `Failed to update technician with ID ${id}`);
      })
    );
  }

  deleteTechnician(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-technician/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new TechnicianNotFoundException());
        }
        return throwError(() => error.message || `Failed to delete technician with ID ${id}`);
      })
    );
  }
}
