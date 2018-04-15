package ro.ubb.socket.server;

import ro.ubb.socket.common.ServiceAG;
import ro.ubb.socket.common.Message;
import ro.ubb.socket.common.ServiceSP;
import ro.ubb.socket.server.service.ServiceServerAG;

import ro.ubb.socket.server.service.ServiceServerSP;
import ro.ubb.socket.server.tcp.TcpServer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpServer tcpServer = new TcpServer(executorService, ServiceAG.SERVER_HOST, ServiceAG.SERVER_PORT);
        ServiceAG serviceAG = new ServiceServerAG(executorService);
        ServiceSP serviceSP = new ServiceServerSP(executorService);

        tcpServer.addHandler(ServiceSP.ADD_STUDENT, (request) -> {
            String res = "";
            try {
                String[] stuff = request.getBody().split(",");
                Long ID = Long.valueOf(stuff[0]);
                String sn = stuff[1];
                String name = stuff[2];
                res = serviceSP.addStudent(ID, sn, name).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });

        tcpServer.addHandler(ServiceSP.DELETE_STUDENT, (request) -> {
            Long res = new Long(-1);
            Long ID = Long.valueOf(request.getBody());
            try {
                res = serviceSP.deleteStudent(ID).get();
                return Message.builder()
                            .header(Message.OK)
                            .body(res.toString())
                            .build();
            } catch (InterruptedException |ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res.toString())
                        .build();
            }
        });

        tcpServer.addHandler(ServiceSP.UPDATE_STUDENT, (request) -> {
            String res = "";
            try {
                String[] stuff = request.getBody().split(",");
                Long ID = Long.valueOf(stuff[0]);
                String sn = stuff[1];
                String name = stuff[2];
                res = serviceSP.updateStudent(ID, sn, name).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });

        tcpServer.addHandler(ServiceSP.PRINT_ALL_STUDENTS, (request) -> {
            String res = "";
            try {
                res = serviceSP.printAllStudents().get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });

        tcpServer.addHandler(ServiceSP.FILTER_STUDENTS, (request) -> {
            String res = "";
            try {
                String name=request.getBody();
                res = serviceSP.filterStudents(name).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });




        tcpServer.addHandler(ServiceSP.ADD_PROBLEM, (request) -> {
            String res = "";
            try {
                String[] stuff = request.getBody().split(",");
                Long ID = Long.valueOf(stuff[0]);
                String description = stuff[1];
                res = serviceSP.addProblem(ID, description).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });

        tcpServer.addHandler(ServiceSP.DELETE_PROBLEM, (request) -> {
            Long res = new Long(-1);
            Long ID = Long.valueOf(request.getBody());
            try {
                res = serviceSP.deleteProblem(ID).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res.toString())
                        .build();
            } catch (InterruptedException |ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res.toString())
                        .build();
            }
        });

        tcpServer.addHandler(ServiceSP.UPDATE_PROBLEM, (request) -> {
            String res = "";
            try {
                String[] stuff = request.getBody().split(",");
                Long ID = Long.valueOf(stuff[0]);
                String description = stuff[1];
                res = serviceSP.updateProblem(ID, description).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });


        tcpServer.addHandler(ServiceSP.PRINT_ALL_PROBLEMS, (request) -> {
            String res = "";
            try {
                res = serviceSP.printAllProblems().get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });

        tcpServer.addHandler(ServiceSP.FILTER_PROBLEMS, (request) -> {
            String res = "";
            try {
                String name=request.getBody();
                res = serviceSP.filterProblems(name).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });




        tcpServer.addHandler(ServiceAG.ADD_ASSIGN, (request) -> {
            String res = "";
            try {
                String[] stuff = request.getBody().split(",");
                Long ID = Long.valueOf(stuff[0]);
                String SID = stuff[1];
                String PID = stuff[2];
                res = serviceAG.addAssign(ID, SID, PID).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });

        tcpServer.addHandler(ServiceAG.DELETE_ASSIGN, (request) -> {
            Long res = new Long(-1);
            Long ID = Long.valueOf(request.getBody());
            try {
                res = serviceAG.deleteAssign(ID).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res.toString())
                        .build();
            } catch (InterruptedException |ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res.toString())
                        .build();
            }
        });

        tcpServer.addHandler(ServiceAG.UPDATE_ASSIGN, (request) -> {
            String res = "";
            try {
                String[] stuff = request.getBody().split(",");
                Long ID = Long.valueOf(stuff[0]);
                String SID = stuff[1];
                String PID = stuff[2];
                res = serviceAG.updateAssign(ID, SID, PID).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });

        tcpServer.addHandler(ServiceAG.PRINT_ALL_ASSIGNS, (request) -> {
            String res = "";
            try {
                res = serviceAG.printAllAssigns().get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });

        tcpServer.addHandler(ServiceAG.FILTER_ASSIGNS, (request) -> {
            String res = "";
            try {
                String name=request.getBody();
                res = serviceAG.filterAssigns(name).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });



        tcpServer.addHandler(ServiceAG.ADD_GRADING, (request) -> {
            String res = "";
            try {
                String[] stuff = request.getBody().split(",");
                Long ID = Long.valueOf(stuff[0]);
                String AID = stuff[1];
                int grade = Integer.valueOf(stuff[2]);
                res = serviceAG.addGrading(ID, AID, grade).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });

        tcpServer.addHandler(ServiceAG.DELETE_GRADING, (request) -> {
            Long res = new Long(-1);
            Long ID = Long.valueOf(request.getBody());
            try {
                res = serviceAG.deleteGrading(ID).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res.toString())
                        .build();
            } catch (InterruptedException |ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res.toString())
                        .build();
            }
        });

        tcpServer.addHandler(ServiceAG.UPDATE_GRADING, (request) -> {
            String res = "";
            try {
                String[] stuff = request.getBody().split(",");
                Long ID = Long.valueOf(stuff[0]);
                String AID = stuff[1];
                int grade = Integer.valueOf(stuff[2]);
                res = serviceAG.updateGrading(ID, AID, grade).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });

        tcpServer.addHandler(ServiceAG.PRINT_ALL_GRADINGS, (request) -> {
            String res = "";
            try {
                res = serviceAG.printAllGradings().get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });

        tcpServer.addHandler(ServiceAG.FILTER_GRADINGS, (request) -> {
            String res = "";
            try {
                String name=request.getBody();
                res = serviceAG.filterGradings(name).get();
                return Message.builder()
                        .header(Message.OK)
                        .body(res)
                        .build();

            } catch (InterruptedException | ExecutionException e) {
                return Message.builder()
                        .header(Message.ERROR)
                        .body(res)
                        .build();
            }

        });


        tcpServer.startServer();

        System.out.println("bye - server");
    }
}
