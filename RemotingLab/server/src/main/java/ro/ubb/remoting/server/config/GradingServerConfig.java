package ro.ubb.remoting.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.remoting.common.AssignService;
import ro.ubb.remoting.common.GradingService;
import ro.ubb.remoting.common.ProblemService;
import ro.ubb.remoting.server.service.GradingServiceServer;
import ro.ubb.remoting.server.service.ProblemServiceServer;

@Configuration
public class GradingServerConfig {
    @Bean
    GradingService GradingService() {
        return new GradingServiceServer();
    }

    @Bean
    RmiServiceExporter rmiServiceExporterg() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("GradingService");
        exporter.setServiceInterface(GradingService.class);
        exporter.setService(GradingService());
        return exporter;
    }
}
