import { TestBed } from '@angular/core/testing';
import { AuthService } from './auth.service';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';

describe('AuthService', () => {
  let service: AuthService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        AuthService,
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });

    service = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);

    localStorage.clear();
  });

  afterEach(() => {
    httpMock.verify();
  });

  // 🔵 LOGIN
  test('should call login API and return token', () => {
    const mockResponse = { token: 'abc123' };

    service.login('karim', 'pwd').subscribe(res => {
      expect(res.token).toBe('abc123');
    });

    const req = httpMock.expectOne('http://localhost:8080/api/auth/login');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({ login: 'karim', password: 'pwd' });

    req.flush(mockResponse);
  });

  // 🔵 REGISTER
  test('should call register API', () => {
    const mockUser = { login: 'karim', password: 'pwd' };
    const mockResponse = { id: 1, login: 'karim' };

    service.register(mockUser).subscribe(res => {
      expect(res.login).toBe('karim');
    });

    const req = httpMock.expectOne('http://localhost:8080/api/auth/register');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockUser);

    req.flush(mockResponse);
  });

  // 🔵 SAVE TOKEN
  test('should save token in localStorage', () => {
    service.saveToken('abc123');
    expect(localStorage.getItem('token')).toBe('abc123');
  });

  // 🔵 GET TOKEN
  test('should return token from localStorage', () => {
    localStorage.setItem('token', 'xyz789');
    expect(service.getToken()).toBe('xyz789');
  });

  // 🔵 LOGOUT
  test('should remove token from localStorage', () => {
    localStorage.setItem('token', 'xyz789');
    service.logout();
    expect(localStorage.getItem('token')).toBeNull();
  });

  // 🔵 IS LOGGED
  test('should return true when token exists', () => {
    localStorage.setItem('token', 'abc');
    expect(service.isLogged()).toBe(true);
  });

  test('should return false when token does not exist', () => {
    expect(service.isLogged()).toBe(false);
  });
});
