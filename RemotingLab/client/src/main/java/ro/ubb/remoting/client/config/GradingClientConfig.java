package ro.ubb.remoting.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.remoting.client.service.GradingServiceClient;
import ro.ubb.remoting.common.GradingService;

@Configuration
public class GradingClientConfig {
    @Bean
    GradingServiceClient GradingServiceClient() {
        return new GradingServiceClient();
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBeang() {
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
        proxy.setServiceInterface(GradingService.class);
        proxy.setServiceUrl("rmi://localhost:1099/GradingService");
        return proxy;
    }
}