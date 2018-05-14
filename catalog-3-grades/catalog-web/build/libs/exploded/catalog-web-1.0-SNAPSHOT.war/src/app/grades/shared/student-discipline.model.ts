export class StudentDiscipline {

    studentId: number;
    disciplineId: number;
    disciplineName: string;
    grade: number;

    constructor(studentId: number, disciplineId: number, disciplineName: string, grade: number) {
        this.studentId = studentId;
        this.disciplineId = disciplineId;
        this.disciplineName = disciplineName;
        this.grade = grade;
    }
}