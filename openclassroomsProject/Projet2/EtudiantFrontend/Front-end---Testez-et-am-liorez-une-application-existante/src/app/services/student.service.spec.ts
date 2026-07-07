import { TestBed } from '@angular/core/testing';
import { StudentService } from './student.service';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { Student } from '../shared/student.model';

describe('StudentService', () => {
  let service: StudentService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        StudentService,
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });

    service = TestBed.inject(StudentService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  test('should retrieve all students', () => {
    const mockStudents: Student[] = [
      { id: 1, firstName: 'John', lastName: 'Doe', email: 'john@example.com' },
      { id: 2, firstName: 'Ali', lastName: 'Ben', email: 'ali@example.com' }
    ];

    service.getAll().subscribe(students => {
      expect(students.length).toBe(2);
      expect(students).toEqual(mockStudents);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/students');
    expect(req.request.method).toBe('GET');
    req.flush(mockStudents);
  });

  test('should retrieve a student by id', () => {
    const mockStudent: Student = {
      id: 1,
      firstName: 'John',
      lastName: 'Doe',
      email: 'john@example.com'
    };

    service.getById(1).subscribe(student => {
      expect(student).toEqual(mockStudent);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/students/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockStudent);
  });

  test('should create a new student', () => {
    const newStudent: Student = {
      firstName: 'John',
      lastName: 'Doe',
      email: 'john@example.com'
    };

    const mockResponse: Student = {
      id: 3,
      firstName: 'John',
      lastName: 'Doe',
      email: 'john@example.com'
    };

    service.create(newStudent).subscribe(student => {
      expect(student).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/students');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newStudent);
    req.flush(mockResponse);
  });

  test('should update a student', () => {
    const updatedStudent: Student = {
      id: 1,
      firstName: 'John',
      lastName: 'Updated',
      email: 'john.updated@example.com'
    };

    service.update(1, updatedStudent).subscribe(student => {
      expect(student).toEqual(updatedStudent);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/students/1');
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updatedStudent);
    req.flush(updatedStudent);
  });

  test('should delete a student', () => {
    service.delete(1).subscribe(response => {
      expect(response).toBeUndefined();
    });

    const req = httpMock.expectOne('http://localhost:8080/api/students/1');
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });
});
