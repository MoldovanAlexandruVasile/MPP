package ro.ubb.LabProb;

import ro.ubb.LabProb.Domain.Assign;
import ro.ubb.LabProb.Domain.Grading;
import ro.ubb.LabProb.Domain.Problem;
import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.*;
import ro.ubb.LabProb.Repository.DataBase.AssignDatabase;
import ro.ubb.LabProb.Repository.DataBase.GradingDatabase;
import ro.ubb.LabProb.Repository.DataBase.ProblemDatabase;
import ro.ubb.LabProb.Repository.DataBase.StudentDatabase;
import ro.ubb.LabProb.Repository.DataBaseRepository;
import ro.ubb.LabProb.Repository.FileRepository.AssignFileRepository;
import ro.ubb.LabProb.Repository.FileRepository.GradingFileRepository;
import ro.ubb.LabProb.Repository.FileRepository.ProblemFileRepository;
import ro.ubb.LabProb.Repository.FileRepository.StudentFileRepository;
import ro.ubb.LabProb.Repository.InMemoryRepository;
import ro.ubb.LabProb.Repository.Repository;
import ro.ubb.LabProb.Repository.XMLRepository.XMLAssignRepository;
import ro.ubb.LabProb.Repository.XMLRepository.XMLGradingRepository;
import ro.ubb.LabProb.Repository.XMLRepository.XMLProblemRepository;
import ro.ubb.LabProb.Repository.XMLRepository.XMLStudentRepository;
import ro.ubb.LabProb.Service.AssignService;
import ro.ubb.LabProb.Service.GradingService;
import ro.ubb.LabProb.Service.ProblemService;
import ro.ubb.LabProb.Service.StudentService;
import ro.ubb.LabProb.UI.Console;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        while (true) {
            Validator<Student> studentValidator = new StudentValidator();
            Validator<Problem> problemValidator = new ProblemValidator();
            Validator<Assign> assignValidator = new AssignValidator();
            Validator<Grading> gradingValidator = new GradingValidator();

            System.out.println("\n\n1. In memory repo.");
            System.out.println("2. .TXT files.");
            System.out.println("3. .XML files.");
            System.out.println("4. Database.");
            System.out.println("0. Exit.");
            System.out.print("\tInput an option: ");
            Scanner scan = new Scanner(System.in);
            int s = scan.nextInt();

            if (s == 1) {
                Repository<Long, Student> studentRepository = new InMemoryRepository<>(studentValidator);
                StudentService studentService = new StudentService(studentRepository);
                populateStudentRepo(studentService);

                Repository<Long, Problem> problemRepository = new InMemoryRepository<>(problemValidator);
                ProblemService problemService = new ProblemService(problemRepository);
                populateProblemRepo(problemService);

                Repository<Long, Assign> assignRepository = new InMemoryRepository<>(assignValidator);
                AssignService assignService = new AssignService(assignRepository);
                populateAssignRepo(assignService);

                Repository<Long, Grading> gradingRepository = new InMemoryRepository<>(gradingValidator);
                GradingService gradingService = new GradingService(gradingRepository);
                populateGradingRepo(gradingService);

                Console console = new Console(studentService, problemService, assignService, gradingService);
                console.runConsole();

            } else if (s == 2) {
                StudentFileRepository studentRepository = new StudentFileRepository(studentValidator, "E:\\Programe\\IntelliJ IDEA 2017.2.5\\Projects\\MPP\\LabProb\\src\\main\\java\\ro\\ubb\\LabProb\\DataFileRepository\\TXTFiles\\StudentFileRepository.txt");
                StudentService studentService = new StudentService(studentRepository);

                ProblemFileRepository problemRepository = new ProblemFileRepository(problemValidator, "E:\\Programe\\IntelliJ IDEA 2017.2.5\\Projects\\MPP\\LabProb\\src\\main\\java\\ro\\ubb\\LabProb\\DataFileRepository\\TXTFiles\\ProblemFileRepository.txt");
                ProblemService problemService = new ProblemService(problemRepository);

                AssignFileRepository assignRepository = new AssignFileRepository(assignValidator, "E:\\Programe\\IntelliJ IDEA 2017.2.5\\Projects\\MPP\\LabProb\\src\\main\\java\\ro\\ubb\\LabProb\\DataFileRepository\\TXTFiles\\AssignFileRepository.txt");
                AssignService assignService = new AssignService(assignRepository);

                GradingFileRepository gradingRepository = new GradingFileRepository(gradingValidator, "E:\\Programe\\IntelliJ IDEA 2017.2.5\\Projects\\MPP\\LabProb\\src\\main\\java\\ro\\ubb\\LabProb\\DataFileRepository\\TXTFiles\\GradingFileRepository.txt");
                GradingService gradingService = new GradingService(gradingRepository);

                Console console = new Console(studentService, problemService, assignService, gradingService);
                console.runConsole();

            } else if (s == 3) {
                XMLStudentRepository xmlStudentRepository = new XMLStudentRepository(studentValidator, "E:\\Programe\\IntelliJ IDEA 2017.2.5\\Projects\\MPP\\LabProb\\src\\main\\java\\ro\\ubb\\LabProb\\DataFileRepository\\XMLFiles\\Students.xml");
                StudentService studentService = new StudentService(xmlStudentRepository);

                XMLProblemRepository xmlProblemRepository = new XMLProblemRepository(problemValidator, "E:\\Programe\\IntelliJ IDEA 2017.2.5\\Projects\\MPP\\LabProb\\src\\main\\java\\ro\\ubb\\LabProb\\DataFileRepository\\XMLFiles\\Problems.xml");
                ProblemService problemService = new ProblemService(xmlProblemRepository);

                XMLAssignRepository assignRepository = new XMLAssignRepository(assignValidator, "E:\\Programe\\IntelliJ IDEA 2017.2.5\\Projects\\MPP\\LabProb\\src\\main\\java\\ro\\ubb\\LabProb\\DataFileRepository\\XMLFiles\\Assigns.xml");
                AssignService assignService = new AssignService(assignRepository);

                XMLGradingRepository gradingRepository = new XMLGradingRepository(gradingValidator, "E:\\Programe\\IntelliJ IDEA 2017.2.5\\Projects\\MPP\\LabProb\\src\\main\\java\\ro\\ubb\\LabProb\\DataFileRepository\\XMLFiles\\Gradings.xml");
                GradingService gradingService = new GradingService(gradingRepository);

                Console console = new Console(studentService, problemService, assignService, gradingService);
                console.runConsole();
            } else if (s == 4){
                DataBaseRepository dataBaseRepository = new DataBaseRepository("School");

                StudentDatabase studentDatabase = new StudentDatabase(dataBaseRepository);
                ProblemDatabase problemDatabase = new ProblemDatabase(dataBaseRepository);
                AssignDatabase assignDatabase = new AssignDatabase(dataBaseRepository);
                GradingDatabase gradingDatabase = new GradingDatabase(dataBaseRepository);

                StudentService studentService = new StudentService(studentDatabase);
                ProblemService problemService = new ProblemService(problemDatabase);
                AssignService assignService = new AssignService(assignDatabase);
                GradingService gradingService = new GradingService(gradingDatabase);

                Console console = new Console(studentService, problemService, assignService, gradingService);
                console.runConsole();
            } else break;
        }
    }

    private static void populateStudentRepo(StudentService ss) {
        Student s1 = new Student("2172", "Alex");
        s1.setId(new Long(1));

        Student s2 = new Student("2173", "Andreea");
        s2.setId(new Long(2));

        Student s3 = new Student("2174", "Tudor");
        s3.setId(new Long(3));

        ss.addStudent(s1);
        ss.addStudent(s2);
        ss.addStudent(s3);
    }

    private static void populateProblemRepo(ProblemService ps) {
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

    private static void populateAssignRepo(AssignService as) {
        Assign a1 = new Assign("1", "2");
        a1.setId(new Long(1));

        Assign a2 = new Assign("2", "1");
        a2.setId(new Long(2));

        Assign a3 = new Assign("3", "3");
        a3.setId(new Long(3));

        as.addAssign(a1);
        as.addAssign(a2);
        as.addAssign(a3);
    }

    private static void populateGradingRepo(GradingService gs) {
        Grading g1 = new Grading("1", 9);
        g1.setId(new Long(1));

        Grading g2 = new Grading("2", 7);
        g2.setId(new Long(2));

        Grading g3 = new Grading("3", 4);
        g3.setId(new Long(3));

        gs.addGrading(g1);
        gs.addGrading(g2);
        gs.addGrading(g3);
    }
}