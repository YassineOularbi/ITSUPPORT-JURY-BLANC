import { Injectable } from '@angular/core';
import * as jwt_decode from 'jwt-decode';
import { UserDto } from '../dtos/user-dto.model';
import { LoginRequest } from '../interfaces/login-request.interface';
import { Role } from '../enums/role.enum';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  extractUsername(token: string): string {
    return this.extractClaim<string>(token, 'sub');
  }

  extractRole(token: string): Role {
    return this.extractClaim<Role>(token, 'role');
  }

  extractUser(token: string): UserDto {
    return this.extractClaim<UserDto>(token, 'user');
  }

  extractExpiration(token: string): Date {
    const expiration = this.extractClaim<number>(token, 'exp');
    return new Date(expiration * 1000);
  }

  private extractClaim<T>(token: string, claim: string): T {
    const decodedToken = jwt_decode.jwtDecode<any>(token);
    return decodedToken[claim];
  }

  isTokenExpired(token: string): boolean {
    const expiration = this.extractExpiration(token);
    return expiration < new Date();
  }

  validateToken(token: string, userDetails: LoginRequest): boolean {
    const username = this.extractUsername(token);
    return username === userDetails.username && !this.isTokenExpired(token);
  }
}
