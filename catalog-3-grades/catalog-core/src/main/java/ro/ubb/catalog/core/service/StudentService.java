package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface StudentService {
    Optional<Student> findStudent(Long studentId);

    List<Student> findAll();

    Student updateStudent(Long studentId, String serialNumber, String name, Set<Long> grades);

    Student createStudent(String serialNumber, String name);

    void deleteStudent(Long studentId);

    Optional<Student> updateStudentGrades(Long studentId, Map<Long, Integer> grades);

}
