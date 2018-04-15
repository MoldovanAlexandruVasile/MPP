package ro.ubb.remoting.server.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean
    JdbcOperations jdbcOperations() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(Driver.class.getName());
        ds.setUrl("jdbc:postgresql://localhost:5432/School");
        ds.setUsername("postgres");
        ds.setPassword("mpp");
        ds.setInitialSize(2);
        return ds;
    }
}
