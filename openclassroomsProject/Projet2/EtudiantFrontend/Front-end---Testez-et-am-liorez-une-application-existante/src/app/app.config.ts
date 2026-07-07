import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AuthInterceptor } from './interceptors/auth.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),

    // ✔ HttpClient + Interceptor Angular 19
    provideHttpClient(
      withInterceptors([AuthInterceptor])
    ),

    // ✔ Active ngModel + ngSubmit en Angular 19
    importProvidersFrom(FormsModule)
  ]
};
