import {Component} from '@angular/core';
import {StudentService} from "../students/shared/student.service";
import {StudentDisciplineService} from "./shared/student-discipline.service";
import {StudentDiscipline} from "./shared/student-discipline.model";
import {Location} from "@angular/common";
import {Student} from "../students/shared/student.model";

@Component({
    moduleId: module.id,
    selector: 'ubb-grades',
    templateUrl: './grades.component.html',
    styleUrls: ['./grades.component.css'],
})
export class GradesComponent {
    errorMessage: string;
    showDisciplinesAndGrades: boolean;
    studentDisciplines: StudentDiscipline[];
    selectedStudent: Student;


    constructor(private studentService: StudentService,
                private studentDisciplineService: StudentDisciplineService,
                private location: Location) {
    }

    loadDisciplinesAndGrades(serialNumber: string) {
        this.showDisciplinesAndGrades = false;
        if (!serialNumber) {
            console.log("serial number must not be empty");
            alert("serial number must not be empty");
            return;
        }
        this.loadStudentDisciplinesForStudent(serialNumber);
    }

    private loadStudentDisciplinesForStudent(serialNumber: string) {
        this.studentService.getStudents()
            .subscribe(
                students => {
                    const studentArr = students.filter(s => s.serialNumber === serialNumber);
                    //TODO handle errors (studentArr should contain one student)
                    if (studentArr && studentArr.length === 1) {
                        this.showDisciplinesAndGrades = true;
                        const student = studentArr[0];
                        this.selectedStudent = student;
                        this.studentDisciplineService.getStudentDisciplines(student.id)
                            .subscribe(
                                studentDisciplines => this.studentDisciplines = studentDisciplines,
                                error => this.errorMessage = error)
                    } else {
                        console.log("studentArr ", studentArr);
                    }
                },
                error => this.errorMessage = <any>error);
    }

    save(studentDisciplineForm) {
        let grades = studentDisciplineForm.form.value;
        console.log("grades: ", grades);
        this.studentDisciplineService.saveGrades(this.selectedStudent.id, grades)
            .subscribe(_ => this.goBack());
    }

    private goBack(): void {
        this.location.back();
    }
}
