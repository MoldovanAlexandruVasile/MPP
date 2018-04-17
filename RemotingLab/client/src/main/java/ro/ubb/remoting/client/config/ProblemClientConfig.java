package ro.ubb.remoting.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.remoting.client.service.ProblemServiceClient;
import ro.ubb.remoting.common.ProblemService;

@Configuration
public class ProblemClientConfig {
    @Bean
    ProblemServiceClient problemServiceClient() {
        return new ProblemServiceClient();
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBeanp() {
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
        proxy.setServiceInterface(ProblemService.class);
        proxy.setServiceUrl("rmi://localhost:1099/ProblemService");
        return proxy;
    }
}