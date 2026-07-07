import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './register.component.html',
})
export class RegisterComponent {

  firstName = '';
  lastName = '';
  login = '';
  password = '';

  constructor(private http: HttpClient) {}

  register() {
    console.log('register() déclenché');
    const body = {
      firstName: this.firstName,
      lastName: this.lastName,
      login: this.login,
      password: this.password
    };

    this.http.post('http://localhost:8080/api/auth/register', body)
      .subscribe({
        next: () => alert('Inscription réussie'),
        error: (err) => alert('Erreur : ' + err.error.message)
      });
  }
}
