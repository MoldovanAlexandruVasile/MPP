package ro.ubb.remoting.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.remoting.client.service.AssignServiceClient;
import ro.ubb.remoting.common.AssignService;

@Configuration
public class AssignClientConfig {
    @Bean
    AssignServiceClient AssignServiceClient() {
        return new AssignServiceClient();
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBeana() {
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
        proxy.setServiceInterface(AssignService.class);
        proxy.setServiceUrl("rmi://localhost:1099/AssignService");
        return proxy;
    }
}