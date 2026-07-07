import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  // 🔵 LOGIN
  login(login: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { login, password });
  }

  // 🔵 REGISTER
  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  // 🔵 SAUVEGARDE DU TOKEN
  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  // 🔵 RÉCUPÉRATION DU TOKEN
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // 🔵 SUPPRESSION DU TOKEN
  logout(): void {
    localStorage.removeItem('token');
  }

  // 🔵 VÉRIFICATION DE CONNEXION
  isLogged(): boolean {
    return !!localStorage.getItem('token');
  }
}
