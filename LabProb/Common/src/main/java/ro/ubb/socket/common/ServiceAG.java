package ro.ubb.socket.common;

import java.util.concurrent.Future;

public interface ServiceAG {
    String SERVER_HOST = "127.0.0.1";
    int SERVER_PORT = 1234;

    String ADD_ASSIGN = "AddAssign";
    String DELETE_ASSIGN = "DeleteAssign";
    String UPDATE_ASSIGN = "UpdateAssign";
    String PRINT_ALL_ASSIGNS = "PrintAllAssign";
    String FILTER_ASSIGNS = "FilterAssign";

    String ADD_GRADING = "AddGrading";
    String DELETE_GRADING = "DeleteGrading";
    String UPDATE_GRADING = "UpdateGrading";
    String PRINT_ALL_GRADINGS = "PrintAllGrading";
    String FILTER_GRADINGS = "FilterGrading";


    Future<String> addAssign(Long ID, String SID, String PID);

    Future<Long> deleteAssign(Long ID);

    Future<String> updateAssign(Long ID, String SID, String PID);

    Future<String> printAllAssigns();

    Future<String> filterAssigns(String name);

    Future<String> addGrading(Long ID, String AID, Integer grade);

    Future<Long> deleteGrading(Long ID);

    Future<String> updateGrading(Long ID, String AID, Integer grade);

    Future<String> printAllGradings();

    Future<String> filterGradings(String name);
}
