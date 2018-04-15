package ro.ubb.socket.client;

import ro.ubb.socket.client.service.ServiceClientAG;
import ro.ubb.socket.client.service.ServiceClientSP;
import ro.ubb.socket.client.tcp.TcpClient;
import ro.ubb.socket.client.ui.ClientConsole;
import ro.ubb.socket.common.ServiceAG;
import ro.ubb.socket.common.ServiceSP;

import javax.xml.ws.Service;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient(executorService, ServiceAG.SERVER_HOST, ServiceAG.SERVER_PORT);
        ServiceAG service = new ServiceClientAG(executorService, tcpClient);
        ServiceSP serviceSP = new ServiceClientSP(executorService, tcpClient);
        ClientConsole clientConsole = new ClientConsole(service, serviceSP);
        clientConsole.runConsole();

        executorService.shutdownNow();

        System.out.println("bye - client");
    }
}
