import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user.model';
import { JwtResponse } from './jwt-response.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  login(user: User): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.apiUrl}/login`, user);
  }

  register(user: User): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.apiUrl}/signup`, user);
  }

  storeToken(token: string) {
    localStorage.setItem('jwt', token);
  }
}
