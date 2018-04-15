package ro.ubb.socket.server.service;

import ro.ubb.socket.common.Domain.Problem;
import ro.ubb.socket.common.Domain.Student;
import ro.ubb.socket.common.ServiceSP;
import ro.ubb.socket.server.DataBase.ProblemDatabase;
import ro.ubb.socket.server.DataBase.StudentDatabase;
import ro.ubb.socket.server.DataBase.DataBaseRepository;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServiceServerSP implements ServiceSP {
    private ExecutorService executorService;
    private DataBaseRepository dataBaseRepository = new DataBaseRepository("School");

    private StudentDatabase studentDatabase = new StudentDatabase(dataBaseRepository);
    private ProblemDatabase problemDatabase = new ProblemDatabase(dataBaseRepository);


    private StudentService studentService = new StudentService(studentDatabase);
    private ProblemService problemService = new ProblemService(problemDatabase);

    public ServiceServerSP(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Future<String> addStudent(Long ID, String sn, String name) {
        return executorService.submit(() -> {
            Student student = new Student(sn, name);
            student.setId(ID);
            studentService.addStudent(student);
            return student.toString();
        });
    }

    @Override
    public Future<Long> deleteStudent(Long ID) {
        return executorService.submit(() -> {
            studentService.deleteStudent(ID);
            return ID;
        });
    }

    @Override
    public Future<String> updateStudent(Long ID, String sn, String name) {
        return executorService.submit(() -> {
            Student student = new Student(sn, name);
            student.setId(ID);
            studentService.updateStudent(student);
            return student.toString();
        });
    }

    @Override
    public Future<String> printAllStudents() {
        return executorService.submit(() -> {
            Set<Student> allStudents = studentService.getAllStudents();
            String s = "";
            for (Student student : allStudents) {
                s += student.toString() +",";
            }
            return s;
        });
    }

    @Override
    public Future<String> filterStudents(String name)
    {
        return executorService.submit(() -> {
            Set<Student> allStudents = studentService.filterStudentsByName(name);
            String s = "";
            for (Student student : allStudents) {
                s += student.toString() +",";
            }
            return s;
        });
    }

    @Override
    public Future<String> addProblem(Long ID, String description) {
        return executorService.submit(() -> {
            Problem problem = new Problem(description);
            problem.setId(ID);
            problemService.addProblem(problem);
            return problem.toString();
        });
    }

    @Override
    public Future<Long> deleteProblem(Long ID) {
        return executorService.submit(() -> {
            problemService.deleteProblem(ID);
            return ID;
        });
    }

    @Override
    public Future<String> updateProblem(Long ID, String description) {
        return executorService.submit(() -> {
            Problem problem = new Problem(description);
            problem.setId(ID);
            problemService.updateProblem(problem);
            return problem.toString();
        });
    }

    @Override
    public Future<String> printAllProblems() {
        return executorService.submit(() -> {
            Set<Problem> allProblems = problemService.getAllProblems();
            String s = "";
            for (Problem problem : allProblems) {
                s += problem.toString() +",";
            }
            return s;
        });
    }

    @Override
    public Future<String> filterProblems(String desc)
    {
        return executorService.submit(() -> {
            Set<Problem> allProblems = problemService.filterProblemsByDescr(desc);
            String s = "";
            for (Problem problem : allProblems) {
                s += problem.toString() +",";
            }
            return s;
        });
    }
}
