package ro.ubb.remoting.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.remoting.common.Student;
import ro.ubb.remoting.common.StudentService;

import java.util.List;

public class StudentServiceClient implements StudentService {

    @Autowired
    StudentService studentService;

    @Override
    public Student findStudent(Long id) { return studentService.findStudent(id); }

    @Override
    public void addStudent(Student student) { studentService.addStudent(student); }

    @Override
    public void updateStudent(Student student) {
        studentService.updateStudent(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentService.deleteStudent(id);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }
}