package ro.ubb.remoting.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.remoting.server.repository.ProblemRepo;
import ro.ubb.remoting.server.repository.ProblemRepositoryImpl;


@Configuration
public class ProblemRepoConfig {
    @Bean
    ProblemRepo problemRepo() {
        return new ProblemRepositoryImpl();
    }
}
