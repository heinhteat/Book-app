package com.example.myapp;

import com.example.myapp.dao.RolesDao;
import com.example.myapp.ds.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class MyAppApplication {

    @Autowired
    private RolesDao rolesDao;

    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }

    @Bean
    @Profile("dev")
    @Transactional
    public ApplicationRunner runner(){
        return r -> {
            Roles admin = new Roles();
            admin.setRoleName("ROLE_ADMIN");
            Roles user = new Roles();
            user.setRoleName("ROLE_USER");
            rolesDao.save(admin);
            rolesDao.save(user);
        };
    }

}
