package ro.ubb.jpa.service;

import ro.ubb.jpa.domain.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Integer id);
}
