import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  login: string = '';
  password: string = '';
  errorMessage: string = '';

  private authService = inject(AuthService);
  private router = inject(Router);

  onSubmit(): void {
    this.authService.login(this.login, this.password).subscribe({
      next: (response: any) => {
        this.authService.saveToken(response.token);
        /*this.router.navigate(['/home']);*/
        //redirige vers le portail student pour gérer les etudiants
        this.router.navigate(['/students']);
      },
      error: () => {
        this.errorMessage = 'Identifiants incorrects';
      }
    });
  }
}
