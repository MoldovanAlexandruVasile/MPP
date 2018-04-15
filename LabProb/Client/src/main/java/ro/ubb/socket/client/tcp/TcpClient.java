package ro.ubb.socket.client.tcp;

import ro.ubb.socket.common.ServiceException;
import ro.ubb.socket.common.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class TcpClient {
    private ExecutorService executorService;
    private String serverHost;
    private int serverPort;

    public TcpClient(ExecutorService executorService, String serverHost, int serverPort) {
        this.executorService = executorService;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public Message sendAndReceive(Message request) {
        try (Socket socket = new Socket(serverHost, serverPort);
             OutputStream os = socket.getOutputStream();
             InputStream is = socket.getInputStream();
        ) {
            request.writeTo(os);
            System.out.println("client -sending request: " + request);

//            Message response = new Message();
            Message response = Message.builder().build();
            response.readFrom(is);
            System.out.println("client - received response: " + response);

            return response;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }
}
