import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UserUpdateDto } from '../dtos/user-update-dto.model';
import { Client } from '../models/client.model';
import { environment } from '../../../environments/environment.development';
import { ClientNotFoundException } from '../exceptions/client-not-found.exception';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl = `${environment.apiUrl}/api/client`;

  constructor(private http: HttpClient) {}

  getAllClients(): Observable<Client[]> {
    return this.http.get<Client[]>(`${this.apiUrl}/get-all-clients`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new ClientNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve all clients`);
      })
    );
  }

  getClientById(id: string): Observable<Client> {
    return this.http.get<Client>(`${this.apiUrl}/get-client-by-id/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new ClientNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve client with ID ${id}`);
      })
    );
  }

  updateClient(id: string, userUpdateDto: UserUpdateDto): Observable<Client> {
    return this.http.put<Client>(`${this.apiUrl}/update-client/${id}`, userUpdateDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new ClientNotFoundException());
        }
        return throwError(() => error.message || `Failed to update client with ID ${id}`);
      })
    );
  }

  deleteClient(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-client/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new ClientNotFoundException());
        }
        return throwError(() => error.message || `Failed to delete client with ID ${id}`);
      })
    );
  }
}
