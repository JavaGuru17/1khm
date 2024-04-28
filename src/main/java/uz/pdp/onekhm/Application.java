package uz.pdp.onekhm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.pdp.onekhm.domain.Role;
import uz.pdp.onekhm.repo.RoleRepository;

@SpringBootApplication
@EnableJpaAuditing
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByCode("ROLE_USER").isEmpty()) {
                roleRepository.save(new Role(null,"ROLE_USER","Role for users",null));
            }
        };
    }
}
