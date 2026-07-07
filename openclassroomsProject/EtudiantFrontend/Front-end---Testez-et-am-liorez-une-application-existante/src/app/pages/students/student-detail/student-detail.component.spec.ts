import { ComponentFixture, TestBed } from '@angular/core/testing';
import { StudentDetailComponent } from './student-detail.component';
import { ActivatedRoute } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { StudentService } from '../../../services/student.service';

describe('StudentDetailComponent', () => {
  let component: StudentDetailComponent;
  let fixture: ComponentFixture<StudentDetailComponent>;

  const mockStudentService = {
    getById: () =>
      of({
        id: 1,
        firstName: 'Karim',
        lastName: 'Test',
        email: 'karim@test.com'
      })
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        StudentDetailComponent,
        HttpClientTestingModule
      ],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: () => '1'
              }
            }
          }
        },
        {
          provide: StudentService,
          useValue: mockStudentService
        }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(StudentDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
