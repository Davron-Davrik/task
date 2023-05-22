package com.optimal.task;

import com.optimal.task.group.GroupEntity;
import com.optimal.task.message.StateMessage;
import com.optimal.task.role.Role;
import com.optimal.task.role.RoleRepo;
import com.optimal.task.role.RoleType;
import com.optimal.task.users.Users;
import com.optimal.task.users.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@RequiredArgsConstructor
@SpringBootApplication
public class TaskApplication implements CommandLineRunner {

    private final RoleRepo roleRepo;
    private final UsersRepo usersRepo;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+5:00"));
        SpringApplication.run(TaskApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        addRole();

//        Optional<Role> optionalRole = roleRepo.findByName(RoleType.ADMIN.getName());
////
//        Users users = new Users(
//                "admin",
//                "admin",
//                passwordEncoder.encode("admin"),
//                List.of(optionalRole.get()),
//                null
//        );

//        usersRepo.save(users);
    }

    public void addRole(){
        roleRepo.save(new Role(RoleType.ADMIN.getName()));
        roleRepo.save(new Role(RoleType.STUDENT.getName()));
    }
}
