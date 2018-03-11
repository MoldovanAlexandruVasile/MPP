package ro.ubb.LabProb;

import ro.ubb.LabProb.Domain.Assign;
import ro.ubb.LabProb.Domain.Problem;
import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.AssignValidator;
import ro.ubb.LabProb.Domain.Validator.ProblemValidator;
import ro.ubb.LabProb.Domain.Validator.StudentValidator;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Repository.InMemoryRepository;
import ro.ubb.LabProb.Repository.Repository;
import ro.ubb.LabProb.Service.AssignService;
import ro.ubb.LabProb.Service.ProblemService;
import ro.ubb.LabProb.Service.StudentService;
import ro.ubb.LabProb.UI.Console;

public class Main
{
    public static void main(String args[])
    {

        Validator<Student> studentValidator = new StudentValidator();
        Repository<Long, Student> studentRepository = new InMemoryRepository<>(studentValidator);
        StudentService studentService = new StudentService(studentRepository);
        populateStudentRepo(studentService);

        Validator<Problem> problemValidator = new ProblemValidator();
        Repository<Long, Problem> problemRepository = new InMemoryRepository<>(problemValidator);
        ProblemService problemService = new ProblemService(problemRepository);
        populateProblemRepo(problemService);

        Validator<Assign> assignValidator = new AssignValidator();
        Repository<Long, Assign> assignRepository = new InMemoryRepository<>(assignValidator);
        AssignService assignService = new AssignService(assignRepository);
        populateAssignRepo(assignService);

        Console console = new Console(studentService, problemService, assignService);
        console.runConsole();
    }

    private static void populateStudentRepo(StudentService ss)
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

    private static void populateProblemRepo(ProblemService ps)
    {
        Problem p1 = new Problem("Ana are mere. Cate pere are ana?");
        p1.setId(new Long(1));

        Problem p2 = new Problem("Diagonala unui patrat este?");
        p2.setId(new Long(2));

        Problem p3 = new Problem("Diagonala trapez este?");
        p3.setId(new Long(3));

        ps.addProblem(p1);
        ps.addProblem(p2);
        ps.addProblem(p3);
    }

    private static void populateAssignRepo(AssignService as)
    {
        Assign a1 = new Assign("1","2");
        a1.setId(new Long(1));

        Assign a2 = new Assign("2","1");
        a2.setId(new Long(2));

        Assign a3 = new Assign("3","3");
        a3.setId(new Long(3));

        as.addAssign(a1);
        as.addAssign(a2);
        as.addAssign(a3);
    }
}