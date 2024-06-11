package com.anonymous63.onlinebookstore;

import com.anonymous63.onlinebookstore.configs.Constants;
import com.anonymous63.onlinebookstore.models.Role;
import com.anonymous63.onlinebookstore.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class OnlineBookStoreApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            Role roleAdmin = new Role();
            roleAdmin.setId(Constants.ROLE_ADMIN);
            roleAdmin.setName("ROLE_ADMIN");

            Role roleUser = new Role();
            roleUser.setId(Constants.ROLE_USER);
            roleUser.setName("ROLE_USER");

            List<Role> roles = List.of(roleAdmin, roleUser);
            List<Role> savedRoles = roleRepo.saveAll(roles);
            System.out.println("Roles saved successfully");
            savedRoles.forEach(System.out::println);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
