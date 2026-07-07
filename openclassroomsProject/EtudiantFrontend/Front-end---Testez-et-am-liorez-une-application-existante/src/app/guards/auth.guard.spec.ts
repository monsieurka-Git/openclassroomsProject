import { TestBed } from '@angular/core/testing';
import { CanActivateFn, Router } from '@angular/router';

import { authGuard } from './auth.guard';
import { AuthService } from '../services/auth.service';

describe('authGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
    TestBed.runInInjectionContext(() => authGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        {
          provide: AuthService,
          useValue: {
            isLogged: jest.fn(() => !!localStorage.getItem('token'))
          }
        },
        {
          provide: Router,
          useValue: {
            navigate: jest.fn()
          }
        }
      ]
    });
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });

  it('should allow navigation when token exists', () => {
    localStorage.setItem('token', 'abc');
    expect(executeGuard()).toBe(true);
  });

  it('should block navigation when token is missing', () => {
    localStorage.removeItem('token');
    expect(executeGuard()).toBe(false);
  });
});
