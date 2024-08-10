import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { BreakdownDto } from '../dtos/breakdown-dto.model';
import { Breakdown } from '../models/breakdown.model';
import { environment } from '../../../environments/environment.development';
import { BreakdownNotFoundException } from '../exceptions/breakdown-not-found.exception';

@Injectable({
  providedIn: 'root'
})
export class BreakdownService {
  private apiUrl = `${environment.apiUrl}/api/breakdown`;

  constructor(private http: HttpClient) {}

  createBreakdown(breakdownDto: BreakdownDto): Observable<Breakdown> {
    return this.http.post<Breakdown>(`${this.apiUrl}/admin/create-breakdown`, breakdownDto).pipe(
      catchError((error) => {
        if (error.status === 400) {
          return throwError(() => new Error('Invalid breakdown details'));
        }
        return throwError(() => error.message || `Failed to create breakdown`);
      })
    );
  }

  getAllBreakdowns(): Observable<Breakdown[]> {
    return this.http.get<Breakdown[]>(`${this.apiUrl}/get-all-breakdowns`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new BreakdownNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve breakdowns`);
      })
    );
  }

  getBreakdownById(id: string): Observable<Breakdown> {
    return this.http.get<Breakdown>(`${this.apiUrl}/admin/get-breakdown-by-id/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new BreakdownNotFoundException());
        }
        return throwError(() => error.message || `Failed to retrieve breakdown with ID ${id}`);
      })
    );
  }

  updateBreakdown(id: string, breakdownDto: BreakdownDto): Observable<Breakdown> {
    return this.http.put<Breakdown>(`${this.apiUrl}/admin/update-breakdown/${id}`, breakdownDto).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new BreakdownNotFoundException());
        }
        return throwError(() => error.message || `Failed to update breakdown with ID ${id}`);
      })
    );
  }

  deleteBreakdown(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/admin/delete-breakdown/${id}`).pipe(
      catchError((error) => {
        if (error.status === 404) {
          return throwError(() => new BreakdownNotFoundException());
        }
        return throwError(() => error.message || `Failed to delete breakdown with ID ${id}`);
      })
    );
  }
}
