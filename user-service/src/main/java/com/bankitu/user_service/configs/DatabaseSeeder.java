package com.bankitu.user_service.configs;

import com.bankitu.user_service.entity.role.Role;
import com.bankitu.user_service.entity.role.RoleType;
import com.bankitu.user_service.entity.user.User;
import com.bankitu.user_service.repository.RoleRepository;
import com.bankitu.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseSeeder {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                roleRepository.save(new Role(null, RoleType.ADMIN));
                roleRepository.save(new Role(null, RoleType.TELLER));
                roleRepository.save(new Role(null, RoleType.USER));
                System.out.println("Roles seeded successfully.");
            } else {
                System.out.println("Roles already exist. No seeding needed.");
            }

            if (userRepository.findByRoleId(Long.valueOf("1")) == null) {
                Role role = roleRepository.findUserByRoleType(RoleType.ADMIN);
                User user = new User();
                user.setRole(role);
                user.setPin("123456");
                user.setFullname("admin bank");
                user.setEmail("admin@bankitu.com");

                userRepository.save(user);
                System.out.println("Users seeded successfully.");
            } else {

                System.out.println("Roles already exist. No seeding needed.");
            }

            try {
                String dropTableQuery = "DROP TABLE IF EXISTS data_users";
                String dropTableQueryTeller = "DROP TABLE IF EXISTS data_teller";
                jdbcTemplate.execute(dropTableQuery);
                jdbcTemplate.execute(dropTableQueryTeller);
                System.out.println("Success Delete Tabel");
            } catch (Exception e) {
                System.out.println("Failed Delete Tabel: " + e.getMessage());
            }
        };
    }
}
