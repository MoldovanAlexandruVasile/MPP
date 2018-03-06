package ro.ubb.LabProb;

import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.StudentValidator;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Repository.InMemoryRepository;
import ro.ubb.LabProb.Repository.Repository;
import ro.ubb.LabProb.Service.StudentService;
import ro.ubb.LabProb.Test.TestRepository;
import ro.ubb.LabProb.UI.Console;
import ro.ubb.LabProb.Test.TestStudent;

public class Main
{
    public static void main(String args[])
    {
        TestStudent ts = new TestStudent();
        ts.runTestStudent();

        TestRepository tr = new TestRepository();
        tr.runTestRepository();

        Validator<Student> studentValidator = new StudentValidator();
        Repository<Long, Student> studentRepository = new InMemoryRepository<>(studentValidator);
        StudentService studentService = new StudentService(studentRepository);
        populate(studentService);
        Console console = new Console(studentService);
        console.runConsole();
    }

    private static void populate(StudentService ss)
    {
        Student s1 = new Student("2172", "Alex");
        s1.setId(new Long(1));

        Student s2 = new Student("2173", "Andree");
        s2.setId(new Long(2));

        Student s3 = new Student("2174", "Tudor");
        s3.setId(new Long(3));

        ss.addStudent(s1);
        ss.addStudent(s2);
        ss.addStudent(s3);
    }
}