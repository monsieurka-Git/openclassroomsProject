/// <reference types="jest" />
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { RegisterComponent } from './register.component';
import { FormsModule } from '@angular/forms';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideRouter } from '@angular/router';
import { AuthService } from '../../services/auth.service';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let httpMock: HttpTestingController;
  let authService: { register: jest.Mock };

  beforeEach(async () => {
    authService = {
      register: jest.fn().mockReturnValue(of({}))
    };

    await TestBed.configureTestingModule({
      imports: [
        RegisterComponent,
        FormsModule
      ],
      providers: [
        provideRouter([]),
        provideHttpClient(),
        provideHttpClientTesting(),
        {
          provide: AuthService,
          useValue: authService
        }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    httpMock = TestBed.inject(HttpTestingController);
    fixture.detectChanges();
  });

  test('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should not submit when form is invalid', () => {
    component.firstName = '';
    component.lastName = '';
    component.login = '';
    component.password = '';

    component.register();

    expect(authService.register).not.toHaveBeenCalled();
  });
});
