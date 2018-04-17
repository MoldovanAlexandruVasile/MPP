package ro.ubb.remoting.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.remoting.common.StudentService;
import ro.ubb.remoting.server.service.StudentServiceServer;

@Configuration
public class StudentServerConfig {
    @Bean
    StudentService StudentService() {
        return new StudentServiceServer();
    }

    @Bean
    RmiServiceExporter rmiServiceExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("StudentService");
        exporter.setServiceInterface(StudentService.class);
        exporter.setService(StudentService());
        return exporter;
    }
}
