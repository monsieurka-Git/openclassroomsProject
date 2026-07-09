import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { StudentService } from '../../../services/student.service';
import { Student } from '../../../shared/student.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-student-form',
  standalone: true,
  templateUrl: './student-form.component.html',
  imports: [FormsModule, CommonModule, RouterModule]
})
export class StudentFormComponent implements OnInit {

  student: Student = { firstName: '', lastName: '', email: '' };
  isEdit = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studentService: StudentService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.isEdit = true;
      this.studentService.getById(Number(id)).subscribe(data => {
        this.student = data;
      });
    }
  }

  save() {
    if (this.isEdit) {
      this.studentService.update(this.student.id!, this.student)
        .subscribe(() => this.router.navigate(['/students']));
    } else {
      this.studentService.create(this.student)
        .subscribe(() => this.router.navigate(['/students']));
    }
  }

  onSubmit(): void {
    this.save();
  }
}