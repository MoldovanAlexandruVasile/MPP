package ro.ubb.remoting.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.remoting.common.ProblemService;
import ro.ubb.remoting.server.service.ProblemServiceServer;

@Configuration
public class ProblemServerConfig {
    @Bean
    ProblemService ProblemService() {
        return new ProblemServiceServer();
    }

    @Bean
    RmiServiceExporter rmiServiceExporterp() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("ProblemService");
        exporter.setServiceInterface(ProblemService.class);
        exporter.setService(ProblemService());
        return exporter;
    }
}
