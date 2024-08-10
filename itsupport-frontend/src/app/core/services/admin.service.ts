import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UserUpdateDto } from '../dtos/user-update-dto.model';
import { Admin } from '../models/admin.model';
import { environment } from '../../../environments/environment.development';
import { AdminNotFoundException } from '../exceptions/admin-not-found.exception';

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
          return throwError(() => new AdminNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve admins`);
      })
    );
  }

  getAdminById(id: string): Observable<Admin> {
    return this.http.get<Admin>(`${this.apiUrl}/admin/get-admin-by-id/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new AdminNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve admin with ID ${id}`);
      })
    );
  }

  updateAdmin(id: string, userUpdateDto: UserUpdateDto): Observable<Admin> {
    return this.http.put<Admin>(`${this.apiUrl}/admin/update-admin/${id}`, userUpdateDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new AdminNotFoundException());
        }
        return throwError(() => error.message || `Failed to update admin with ID ${id}`);
      })
    );
  }

  deleteAdmin(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/admin/delete-admin/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new AdminNotFoundException());
        }
        return throwError(() => error.message || `Failed to delete admin with ID ${id}`);
      })
    );
  }
}
