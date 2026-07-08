import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient, private router: Router) {}

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

  // 🔵 SUPPRESSION DU TOKEN + APPEL API BACKEND
  logout(): void {
    const token = this.getToken();
    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
      this.http.post(`${this.apiUrl}/logout`, {}, { headers }).subscribe({
        next: () => console.log('Déconnexion côté backend réussie'),
        error: (err) => console.error('Erreur lors du logout côté backend', err)
      });
    }
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  // 🔵 VÉRIFICATION DE CONNEXION
  isLogged(): boolean {
    return !!localStorage.getItem('token');
  }
}
