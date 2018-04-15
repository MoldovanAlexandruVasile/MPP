package ro.ubb.socket.client.service;

import ro.ubb.socket.client.tcp.TcpClient;
import ro.ubb.socket.common.ServiceAG;
import ro.ubb.socket.common.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServiceClientAG implements ServiceAG {
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ServiceClientAG(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }


    @Override
    public Future<String> addAssign(Long ID, String SID, String PID) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceAG.ADD_ASSIGN)
                    .body(String.valueOf(ID) + "," + SID + "," + PID)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public Future<Long> deleteAssign(Long ID) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceAG.DELETE_ASSIGN)
                    .body(String.valueOf(ID))
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return Long.valueOf(response.getBody());
        });
    }

    @Override
    public Future<String> updateAssign(Long ID, String SID, String PID) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceAG.UPDATE_ASSIGN)
                    .body(String.valueOf(ID) + "," + SID + "," + PID)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }


    @Override
    public Future<String> printAllAssigns() {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceAG.PRINT_ALL_ASSIGNS)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public Future<String> filterAssigns(String name)
    {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceAG.FILTER_ASSIGNS)
                    .body(name)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public Future<String> addGrading(Long ID, String AID, Integer grade) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceAG.ADD_GRADING)
                    .body(String.valueOf(ID) + "," + AID + "," + String.valueOf(grade))
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public Future<Long> deleteGrading(Long ID) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceAG.DELETE_GRADING)
                    .body(String.valueOf(ID))
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return Long.valueOf(response.getBody());
        });
    }

    @Override
    public Future<String> updateGrading(Long ID, String AID, Integer grade) {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceAG.UPDATE_GRADING)
                    .body(String.valueOf(ID) + "," + AID + "," + String.valueOf(grade))
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public Future<String> printAllGradings() {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceAG.PRINT_ALL_GRADINGS)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public Future<String> filterGradings(String name)
    {
        return executorService.submit(() -> {
            Message request = Message.builder()
                    .header(ServiceAG.FILTER_GRADINGS)
                    .body(name)
                    .build();
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }
}
