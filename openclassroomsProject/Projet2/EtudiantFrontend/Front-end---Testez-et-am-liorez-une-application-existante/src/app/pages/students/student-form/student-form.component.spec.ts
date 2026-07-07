import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';

import { StudentFormComponent } from './student-form.component';
import { StudentService } from '../../../services/student.service';

const mockStudent = {
  id: 1,
  firstName: 'John',
  lastName: 'Doe',
  email: 'john.doe@example.com'
};

describe('StudentFormComponent', () => {
  let component: StudentFormComponent;
  let fixture: ComponentFixture<StudentFormComponent>;
  let studentService: { create: jest.Mock; update: jest.Mock; getById: jest.Mock };

  beforeEach(async () => {
    studentService = {
      create: jest.fn().mockReturnValue(of(mockStudent)),
      update: jest.fn().mockReturnValue(of(mockStudent)),
      getById: jest.fn().mockReturnValue(of(mockStudent))
    };

    await TestBed.configureTestingModule({
      imports: [StudentFormComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: () => null
              }
            }
          }
        },
        {
          provide: Router,
          useValue: {
            navigate: jest.fn()
          }
        },
        {
          provide: StudentService,
          useValue: studentService
        }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(StudentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call createStudent when form is valid', () => {
    component.student = mockStudent as any;
    component.onSubmit();

    expect(studentService.create).toHaveBeenCalledWith(mockStudent);
  });
});
