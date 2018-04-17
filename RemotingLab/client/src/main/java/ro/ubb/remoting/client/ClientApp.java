package ro.ubb.remoting.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.remoting.client.service.AssignServiceClient;
import ro.ubb.remoting.client.service.GradingServiceClient;
import ro.ubb.remoting.client.service.ProblemServiceClient;
import ro.ubb.remoting.client.service.StudentServiceClient;
import ro.ubb.remoting.client.ui.ClientConsole;
import ro.ubb.remoting.common.AssignService;
import ro.ubb.remoting.common.GradingService;
import ro.ubb.remoting.common.ProblemService;
import ro.ubb.remoting.common.StudentService;

public class ClientApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.remoting.client.config");

        StudentServiceClient studentService =  context.getBean(StudentServiceClient.class);
        ProblemServiceClient problemService =  context.getBean(ProblemServiceClient.class);
        AssignServiceClient assignService =  context.getBean(AssignServiceClient.class);
        GradingServiceClient gradingService =  context.getBean(GradingServiceClient.class);
        ClientConsole console = new ClientConsole(studentService, problemService,assignService,gradingService);
        console.runConsole();
    }
}