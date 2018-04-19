package ro.ubb.jpa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"ro.ubb.jpa.repository", "ro.ubb.jpa.service", "ro.ubb.jpa.ui"})
public class CatalogConfig {

}
