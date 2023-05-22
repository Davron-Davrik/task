package com.optimal.task.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
    boolean existsByUsername(String username);

    List<Users> findAllByNameContainingIgnoreCase(String name);
    List<Users> findAllByGroupEntity_Id(Long group_id);
}
