import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StudentService } from '../../../services/student.service';
import { Student } from '../../../shared/student.model';

@Component({
  selector: 'app-student-detail',
  standalone: true,
  templateUrl: './student-detail.component.html'
})
export class StudentDetailComponent implements OnInit {

  student!: Student;

  constructor(
    private route: ActivatedRoute,
    private studentService: StudentService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.studentService.getById(id).subscribe(data => {
      this.student = data;
    });
  }
}
