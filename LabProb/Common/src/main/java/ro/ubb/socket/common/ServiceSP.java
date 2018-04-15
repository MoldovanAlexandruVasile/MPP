package ro.ubb.socket.common;

import java.util.concurrent.Future;

public interface ServiceSP {
    String SERVER_HOST = "127.0.0.1";
    int SERVER_PORT = 1234;

    String ADD_STUDENT = "AddStudent";
    String DELETE_STUDENT = "DeleteStudent";
    String UPDATE_STUDENT = "UpdateStudent";
    String PRINT_ALL_STUDENTS = "PrintAllStudent";
    String FILTER_STUDENTS = "FilterStudent";

    String ADD_PROBLEM = "AddProblem";
    String DELETE_PROBLEM = "DeleteProblem";
    String UPDATE_PROBLEM = "UpdateProblem";
    String PRINT_ALL_PROBLEMS = "PrintAllProblem";
    String FILTER_PROBLEMS = "FilterProblem";

    Future<String> addStudent(Long ID, String sn, String name);

    Future<Long> deleteStudent(Long ID);

    Future<String> updateStudent(Long ID, String sn, String name);

    Future<String> printAllStudents();

    Future<String> filterStudents(String name);


    Future<String> addProblem(Long ID, String description);

    Future<Long> deleteProblem(Long ID);

    Future<String> updateProblem(Long ID, String description);

    Future<String> printAllProblems();

    Future<String> filterProblems(String desc);
}
