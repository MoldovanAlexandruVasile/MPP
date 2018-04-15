package ro.ubb.remoting.server.repository;

import ro.ubb.remoting.common.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepo {
    List<Student> findAllStudents();
    void save(Student book);
    void update(Student book);
    void delete(Long id);
    Optional<Student> findOne(Long id);
}