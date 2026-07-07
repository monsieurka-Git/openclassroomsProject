import { Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { authGuard } from './guards/auth.guard';
import { StudentListComponent } from './pages/students/student-list/student-list.component';
import { StudentDetailComponent } from './pages/students/student-detail/student-detail.component';
import { StudentFormComponent } from './pages/students/student-form/student-form.component';


export const routes: Routes = [

  { 
    path: 'login', 
    component: LoginComponent 
  },

  { 
    path: 'register',
    loadComponent: () =>
      import('./pages/register/register.component')
        .then(m => m.RegisterComponent)
  },

  { 
    path: 'home', 
    component: HomeComponent,
    canActivate: [authGuard]
  },

  { 
    path: '', 
    redirectTo: 'login', 
    pathMatch: 'full' 
  },
  
  { path: 'students',
    component: StudentListComponent,
    canActivate: [authGuard] },

  { path: 'students/add',
    component: StudentFormComponent,
    canActivate: [authGuard] },

  { path: 'students/edit/:id',
    component: StudentFormComponent,
    canActivate: [authGuard] },
  { path: 'students/:id',
    component: StudentDetailComponent,
    canActivate: [authGuard] },
  { 
    path: '**', 
    redirectTo: 'login' 
  },
];
