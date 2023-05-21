package com.optimal.task.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);

    boolean existsByUsernameAndDeleteIsFalse(String username);
    boolean existsByUsername(String username);
}
