package ro.ubb.remoting.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.remoting.common.StudentService;
import ro.ubb.remoting.common.Student;
import ro.ubb.remoting.server.repository.StudentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceServer implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<Student> findAllStudents() {
        System.out.println("getAll -- method entered");

        List<Student> result = studentRepo.findAllStudents();

        System.out.println("getAll: result = " + result);

        return result;
    }

    @Override
    public Student findStudent(Long id) {

        System.out.println("findStudent: ID = " + id);

        Student book = studentRepo.findOne(id).get();

        System.out.println("findStudent: Student = " + book);

        return book;

    }

    @Override
    public void addStudent(Student student) {
        System.out.println("addStudent: Student = " + student);

        studentRepo.save(student);

        System.out.println("addStudent -- method finished");
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {

        System.out.println("updateStudent: Student = " + student);

        Optional<Student> studentOptional = studentRepo.findOne(student.getId());

        if (studentOptional.isPresent()) {
            studentRepo.update(student);
        }

        System.out.println("updateStudent: StudentOptional = " + studentOptional);
    }

    @Override
    public void deleteStudent(Long id) {

        System.out.println("deleteStudent: ID = " + id);
        studentRepo.delete(id);
    }
}
