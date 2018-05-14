import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

import {StudentDiscipline} from "./student-discipline.model";


@Injectable()
export class StudentDisciplineService {
  private studentDisciplineUrl = 'http://localhost:8080/api/grades';

  constructor(private httpClient: HttpClient) {
  }

  getStudentDisciplines(studentID: number): Observable<StudentDiscipline[]> {
    return this.httpClient
      .get<StudentDiscipline[]>(`${this.studentDisciplineUrl}/${studentID}`);
  }

  saveGrades(studentId: number, disciplineIdsGrades: Object): Observable<StudentDiscipline[]> {
    console.log("disciplineIdsGrades: ", disciplineIdsGrades);
    const url = `${this.studentDisciplineUrl}/${studentId}`;
    let studentDisciplines = this.createStudentDisciplines(studentId, disciplineIdsGrades);
    console.log("grades: ", studentDisciplines);
    console.log("request: ", JSON.stringify({"studentDisciplines": studentDisciplines}));
    return this.httpClient
      .put<StudentDiscipline[]>(url, studentDisciplines);
  }

  private createStudentDisciplines(studentId: number, disciplineIdsGrades: Object): StudentDiscipline[] {
    const arr: StudentDiscipline[] = [];
    Object.keys(disciplineIdsGrades).forEach(disciplineId => {
      const sd = new StudentDiscipline(
        studentId,
        parseInt(disciplineId),
        null,
        disciplineIdsGrades[disciplineId]);
      arr.push(sd);
    });
    return arr;
  }
}
