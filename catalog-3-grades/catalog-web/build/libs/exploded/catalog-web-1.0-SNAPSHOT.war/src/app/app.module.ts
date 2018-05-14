import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {StudentDetailComponent} from "./students/student-detail/student-detail.component";
import {StudentsComponent} from "./students/students.component";
import {StudentListComponent} from "./students/student-list/student-list.component";
import {StudentService} from "./students/shared/student.service";
import {DisciplinesComponent} from "./disciplines/disciplines.component";
import {DisciplineService} from "./disciplines/shared/discipline.service";
import {StudentNewComponent} from "./students/student-new/student-new.component";
import {DisciplineListComponent} from "./disciplines/discipline-list/discipline-list.component";
import {DisciplineEnrollComponent} from "./discipline-enroll/discipline-enroll.component";
import {GradesComponent} from "./grades/grades.component";
import {StudentDisciplineService} from "./grades/shared/student-discipline.service";


@NgModule({
  declarations: [
    AppComponent,
    StudentDetailComponent,
    StudentsComponent,
    StudentListComponent,
    StudentNewComponent,

    DisciplinesComponent,
    DisciplineListComponent,

    DisciplineEnrollComponent,
    GradesComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [StudentService, DisciplineService, StudentDisciplineService],
  bootstrap: [AppComponent]
})
export class AppModule {
}