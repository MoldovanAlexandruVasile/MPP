package ro.ubb.LabProb.Test;

import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.StudentValidator;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Repository.InMemoryRepository;
import ro.ubb.LabProb.Repository.Repository;
import ro.ubb.LabProb.Service.StudentService;

public class TestRepository
{
    private Validator<Student> studentValidator = new StudentValidator();
    private Repository<Long, Student> studentRepository = new InMemoryRepository<>(studentValidator);
    private StudentService studentService = new StudentService(studentRepository);
    private Student student;

    public void runTestRepository()
    {
        setUpStudent();
        testAddStudent();
        testFilterStudent();
    }

    private void setUpStudent()
    {
        student = new Student("2172", "Alex");
        student.setId(new Long(1));
    }

    private void testAddStudent()
    {
        studentService.addStudent(student);
        assert(studentService.getAllStudents().contains(student));
    }

    private void testFilterStudent()
    {
        assert (studentService.filterStudentsByName("e").contains(student));
    }
}
