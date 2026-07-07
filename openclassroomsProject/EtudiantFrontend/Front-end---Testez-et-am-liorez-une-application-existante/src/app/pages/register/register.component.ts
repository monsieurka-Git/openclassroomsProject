import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html',
})
export class RegisterComponent {

  firstName = '';
  lastName = '';
  login = '';
  password = '';
  errorMessage = '';
  successMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  register() {
    if (!this.firstName || !this.lastName || !this.login || !this.password) {
      this.successMessage = '';
      this.errorMessage = 'Tous les champs sont requis.';
      return;
    }

    const body = {
      firstName: this.firstName,
      lastName: this.lastName,
      login: this.login,
      password: this.password
    };

    this.authService.register(body).subscribe({
      next: () => {
        this.successMessage = 'Inscription réussie. Connectez-vous maintenant.';
        this.errorMessage = '';
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.successMessage = '';
        this.errorMessage = err?.error?.message || 'Erreur lors de l’inscription.';
      }
    });
  }
}
