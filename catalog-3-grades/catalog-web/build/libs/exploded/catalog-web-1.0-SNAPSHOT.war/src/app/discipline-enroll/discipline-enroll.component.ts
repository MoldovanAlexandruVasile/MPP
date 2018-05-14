import {Component} from "@angular/core";
import {Location} from "@angular/common";
import {Discipline} from "../disciplines/shared/discipline.model";
import {StudentService} from "../students/shared/student.service";
import {DisciplineService} from "../disciplines/shared/discipline.service";
import {Student} from "../students/shared/student.model";

@Component({
  moduleId: module.id,
  selector: 'ubb-discipline-enroll',
  templateUrl: './discipline-enroll.component.html',
  styleUrls: ['./discipline-enroll.component.css'],
})
export class DisciplineEnrollComponent {
  errorMessage: string;
  selectedStudent: Student;
  showDisciplines: boolean;
  selectedDisciplines: Discipline[];
  availableDisciplines: Discipline[];

  constructor(private studentService: StudentService,
              private disciplineService: DisciplineService,
              private location: Location) {
  }

  goBack(): void {
    this.location.back();
  }

  loadDisciplines(serialNumber: string) {
    this.showDisciplines = false;
    if (!serialNumber) {
      console.log("serial number must not be empty");
      alert("serial number must not be empty");
      return;
    }
    this.loadAvailableDisciplines();
    this.loadSelectedDisciplines(serialNumber);
  }

  private loadSelectedDisciplines(serialNumber: string) {
    this.studentService.getStudents()
      .subscribe(
        students => {
          const currentStudent = students.filter(s => s.serialNumber === serialNumber);
          //TODO there should be exactly one student in currentStudent or err; handle errs
          if (currentStudent.length === 1) {
            this.selectedStudent = currentStudent[0];
            this.showDisciplines = true;
            this.loadSelectedDisciplinesForStudent(this.selectedStudent);
          }
        },
        error => this.errorMessage = <any>error);
  }

  private loadSelectedDisciplinesForStudent(student: Student) {
    this.selectedDisciplines = [];
    const disciplineIds = student.disciplines;
    if (disciplineIds) {
      disciplineIds.forEach(id => {
        this.disciplineService.getDiscipline(id)
          .subscribe(
            discipline => {
              this.selectedDisciplines = this.selectedDisciplines
                .concat([discipline]);
            },
            error => this.errorMessage = error);
      });
    }
  }

  private loadAvailableDisciplines() {
    this.disciplineService.getDisciplines()
      .subscribe(
        disciplines => this.availableDisciplines = disciplines,
        error => this.errorMessage = <any>error);
  }

  selectDiscipline(discipline: Discipline) {
    const disc = this.selectedDisciplines.filter(d => d === discipline);
    if (disc.length > 0) {
      console.log("discipline already selected");
      alert("discipline already selected");
      return;
    }
    this.selectedDisciplines = this.selectedDisciplines.concat([discipline]);
  }

  removeSelectedDiscipline(discipline: Discipline) {
    this.selectedDisciplines = this.selectedDisciplines.filter(d => d !== discipline);
  }

  save() {
    this.selectedStudent.disciplines = this.selectedDisciplines.map(d => d.id);
    console.log("selectedStudent ", this.selectedStudent);

    this.studentService.update(this.selectedStudent)
      .subscribe(_ => this.goBack());
  }
}
