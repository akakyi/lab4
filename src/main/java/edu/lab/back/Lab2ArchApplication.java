package edu.lab.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {
        "classpath:application.properties",
        "classpath:message.properties"
})
public class Lab2ArchApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab2ArchApplication.class, args);
    }

}
