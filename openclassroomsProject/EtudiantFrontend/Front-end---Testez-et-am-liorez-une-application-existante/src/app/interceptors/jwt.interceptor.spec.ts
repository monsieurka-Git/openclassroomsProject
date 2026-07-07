import { TestBed } from '@angular/core/testing';
import { HttpInterceptorFn } from '@angular/common/http';
import { of } from 'rxjs';

import { jwtInterceptor } from './jwt.interceptor';

describe('jwtInterceptor', () => {
  const interceptor: HttpInterceptorFn = (req, next) =>
    TestBed.runInInjectionContext(() => jwtInterceptor(req, next));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });

  it('should add Authorization header when token exists', () => {
    localStorage.setItem('token', 'abc123');
    const mockRequest = {
      url: 'http://localhost:8080/api/test',
      clone: jest.fn().mockImplementation((options: any) => ({
        url: 'http://localhost:8080/api/test',
        headers: {
          get: (name: string) => options?.setHeaders?.[name] ?? null
        }
      }))
    } as any;
    const next = jest.fn().mockImplementation((req: any) => of(req));

    interceptor(mockRequest as any, next as any);

    expect(next).toHaveBeenCalledTimes(1);
    const handledRequest = next.mock.calls[0][0];
    expect(handledRequest.headers.get('Authorization')).toBe('Bearer abc123');
    localStorage.removeItem('token');
  });
});
