package ro.ubb.remoting.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.remoting.common.AssignService;
import ro.ubb.remoting.common.ProblemService;
import ro.ubb.remoting.server.service.AssignServiceServer;
import ro.ubb.remoting.server.service.ProblemServiceServer;

@Configuration
@ComponentScan("ro.ubb.remoting.server.repository")
public class AssignServerConfig {
    @Bean
    AssignService AssignService() {
        return new AssignServiceServer();
    }

    @Bean
    RmiServiceExporter rmiServiceExportera() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("AssignService");
        exporter.setServiceInterface(AssignService.class);
        exporter.setService(AssignService());
        return exporter;
    }
}
