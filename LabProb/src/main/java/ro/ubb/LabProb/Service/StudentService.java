package ro.ubb.LabProb.Service;

import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.Repository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentService
{
    private Repository<Long, Student> repository;

    public StudentService(Repository<Long, Student> repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) throws ValidatorException { repository.save(student); }

    public Set<Student> getAllStudents()
    {
        Iterable<Student> students = repository.findAll();
        return StreamSupport.stream(students.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Student> filterStudentsByName(String s)
    {
        Iterable<Student> students = repository.findAll();

        Set<Student> filteredStudents= new HashSet<>();
        students.forEach(filteredStudents::add);
        filteredStudents.removeIf(student -> !student.getName().contains(s));

        return filteredStudents;
    }
}
