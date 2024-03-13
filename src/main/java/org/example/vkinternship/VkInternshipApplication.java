package org.example.vkinternship;

import org.example.vkinternship.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
public class VkInternshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(VkInternshipApplication.class, args);
    }

}
