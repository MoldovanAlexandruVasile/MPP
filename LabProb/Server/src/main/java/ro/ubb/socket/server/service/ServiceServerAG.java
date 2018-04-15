package ro.ubb.socket.server.service;

import ro.ubb.socket.common.Domain.Assign;
import ro.ubb.socket.common.Domain.Grading;
import ro.ubb.socket.common.ServiceAG;
import ro.ubb.socket.server.DataBase.AssignDatabase;
import ro.ubb.socket.server.DataBase.DataBaseRepository;
import ro.ubb.socket.server.DataBase.GradingDatabase;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServiceServerAG implements ServiceAG{
    private ExecutorService executorService;
    private DataBaseRepository dataBaseRepository = new DataBaseRepository("School");

    private AssignDatabase assignDatabase = new AssignDatabase(dataBaseRepository);
    private GradingDatabase gradingDatabase = new GradingDatabase(dataBaseRepository);

    private AssignService assignService = new AssignService(assignDatabase);
    private GradingService gradingService = new GradingService(gradingDatabase);

    public ServiceServerAG(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Future<String> addAssign(Long ID, String SID, String PID) {
        return executorService.submit(() -> {
            Assign assign = new Assign(SID, PID);
            assign.setId(ID);
            assignService.addAssign(assign);
            return assign.toString();
        });
    }

    @Override
    public Future<Long> deleteAssign(Long ID) {
        return executorService.submit(() -> {
            assignService.deleteAssign(ID);
            return ID;
        });
    }

    @Override
    public Future<String> updateAssign(Long ID, String SID, String PID) {
        return executorService.submit(() -> {
            Assign assign = new Assign(SID, PID);
            assign.setId(ID);
            assignService.updateAssign(assign);
            return assign.toString();
        });
    }


    @Override
    public Future<String> printAllAssigns() {
        return executorService.submit(() -> {
            Set<Assign> allAssigns = assignService.getAllAssigns();
            String s = "";
            for (Assign assign : allAssigns) {
                s += assign.toString() +",";
            }
            return s;
        });
    }

    @Override
    public Future<String> filterAssigns(String desc)
    {
        return executorService.submit(() -> {
            Set<Assign> allAssigns = assignService.filterAssignsBySID(desc);
            String s = "";
            for (Assign assign : allAssigns) {
                s += assign.toString() +",";
            }
            return s;
        });
    }

    @Override
    public Future<String> addGrading(Long ID, String AID, Integer grade) {
        return executorService.submit(() -> {
            Grading grading = new Grading(AID, grade);
            grading.setId(ID);
            gradingService.addGrading(grading);
            return grading.toString();
        });
    }

    @Override
    public Future<Long> deleteGrading(Long ID) {
        return executorService.submit(() -> {
            gradingService.deleteGrading(ID);
            return ID;
        });
    }

    @Override
    public Future<String> updateGrading(Long ID, String AID, Integer grade) {
        return executorService.submit(() -> {
            Grading grading = new Grading(AID, grade);
            grading.setId(ID);
            gradingService.updateGrading(grading);
            return grading.toString();
        });
    }

    @Override
    public Future<String> printAllGradings() {
        return executorService.submit(() -> {
            Set<Grading> allGradings = gradingService.getAllGradings();
            String s = "";
            for (Grading grading : allGradings) {
                s += grading.toString() +",";
            }
            return s;
        });
    }

    @Override
    public Future<String> filterGradings(String desc)
    {
        return executorService.submit(() -> {
            Set<Grading> allGradings = gradingService.filterGradingsByAID(desc);
            String s = "";
            for (Grading grading : allGradings) {
                s += grading.toString() +",";
            }
            return s;
        });
    }
}
