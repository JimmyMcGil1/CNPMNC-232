package cnpmnc_232.cnpmnc_232_backend;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("cnpmnc_232.cnpmnc_232_backend.repository")
public class Cnpmnc232BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cnpmnc232BackendApplication.class, args);
    }

}
