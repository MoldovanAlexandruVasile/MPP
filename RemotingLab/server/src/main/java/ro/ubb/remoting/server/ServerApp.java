package ro.ubb.remoting.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.remoting.server.repository.AssignRepo;
import ro.ubb.remoting.server.repository.GradingRepo;
import ro.ubb.remoting.server.repository.ProblemRepo;
import ro.ubb.remoting.server.repository.StudentRepo;

public class ServerApp {
    public static void main(String[] args) {
        System.out.println("Server started");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.remoting.server.config");

        StudentRepo studentRepo = context.getBean(StudentRepo.class);
        ProblemRepo problemRepo = context.getBean(ProblemRepo.class);
        AssignRepo assignRepo = context.getBean(AssignRepo.class);
        GradingRepo gradingRepo = context.getBean(GradingRepo.class);
    }
}