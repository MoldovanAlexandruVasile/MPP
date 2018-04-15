package ro.ubb.socket.client.service;

import ro.ubb.socket.client.tcp.TcpClient;
import ro.ubb.socket.common.Message;
import ro.ubb.socket.common.ServiceSP;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServiceClientSP implements ServiceSP {
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ServiceClientSP(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public Future<String> addStudent(Long ID, String sn, String name) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceSP.ADD_STUDENT)
                    .body(String.valueOf(ID) + "," + sn + "," + name)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public Future<Long> deleteStudent(Long ID) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceSP.DELETE_STUDENT)
                    .body(String.valueOf(ID))
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return Long.valueOf(response.getBody());
        });
    }

    @Override
    public Future<String> updateStudent(Long ID, String sn, String name) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceSP.UPDATE_STUDENT)
                    .body(String.valueOf(ID) + "," + sn + "," + name)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public Future<String> printAllStudents() {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceSP.PRINT_ALL_STUDENTS)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public Future<String> filterStudents(String name)
    {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceSP.FILTER_STUDENTS)
                    .body(name)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }


    @Override
    public Future<String> addProblem(Long ID, String description) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceSP.ADD_PROBLEM)
                    .body(String.valueOf(ID) + "," + description)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public Future<Long> deleteProblem(Long ID) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceSP.DELETE_PROBLEM)
                    .body(String.valueOf(ID))
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return Long.valueOf(response.getBody());
        });
    }

    @Override
    public Future<String> updateProblem(Long ID, String description) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceSP.UPDATE_PROBLEM)
                    .body(String.valueOf(ID) + "," + description)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public Future<String> printAllProblems() {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceSP.PRINT_ALL_PROBLEMS)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }



    @Override
    public Future<String> filterProblems(String desc)
    {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceSP.FILTER_PROBLEMS)
                    .body(desc)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }
}
