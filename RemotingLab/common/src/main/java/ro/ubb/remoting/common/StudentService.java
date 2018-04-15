package ro.ubb.remoting.common;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudents();
    Student findStudent(Long id);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Long id);
}
