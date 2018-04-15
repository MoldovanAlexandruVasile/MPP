package ro.ubb.remoting.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.remoting.server.repository.StudentRepo;
import ro.ubb.remoting.server.repository.StudentRepositoryImpl;


@Configuration
public class StudentRepoConfig {
    @Bean
    StudentRepo studentRepo() {
        return new StudentRepositoryImpl();
    }
}
