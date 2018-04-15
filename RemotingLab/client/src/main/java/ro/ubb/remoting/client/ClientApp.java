package ro.ubb.remoting.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.remoting.client.ui.ClientConsole;
import ro.ubb.remoting.common.StudentService;

public class ClientApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.remoting.client.config");

        StudentService studentService = (StudentService) context.getBean("StudentServiceClient");
        ClientConsole console = new ClientConsole(studentService);
        console.runConsole();
    }
}