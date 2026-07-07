import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { StudentService } from '../../../services/student.service';
import { Student } from '../../../shared/student.model';

@Component({
  selector: 'app-student-list',
  standalone: true,
  templateUrl: './student-list.component.html',
  imports: [CommonModule, RouterModule]
})
export class StudentListComponent implements OnInit {

  students: Student[] = [];

  constructor(private studentService: StudentService) {}

  ngOnInit(): void {
    this.studentService.getAll().subscribe(data => {
      this.students = data;
    });
  }

  delete(id: number) {
    this.studentService.delete(id).subscribe(() => {
      this.students = this.students.filter(s => s.id !== id);
    });
  }
}
