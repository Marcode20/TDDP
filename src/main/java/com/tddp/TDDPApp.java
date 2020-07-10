package com.tddp;

import com.tddp.model.Role;
import com.tddp.model.User;
import com.tddp.service.RoleService;
import com.tddp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TDDPApp implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    public static void main(String[] args) {
        SpringApplication.run(TDDPApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        adminRole.setId(1);

        Role userRole = new Role();
        userRole.setName("USER");
        userRole.setId(2);

        List<Role> adminRoleList = new ArrayList<>();

        adminRoleList.add(adminRole);
        //adminRoleList.add(userRole);


        List<Role> userRoleList = new ArrayList<>();
        userRoleList.add(userRole);

        roleService.createRole(adminRole);
        roleService.createRole(userRole);

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("admin");
        adminUser.setPasswordConfirm("admin");
        adminUser.setId(1);

        User userUser = new User();
        userUser.setUsername("user");
        userUser.setPassword("user");
        userUser.setPasswordConfirm("user");
        userUser.setId(2);



        userService.save(adminUser, adminRoleList);

        userService.save(userUser, userRoleList);

    }
}
