package com.optimal.task.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepo extends JpaRepository<GroupEntity, Long> {


    boolean existsAllByNameContainingIgnoreCase(String name);
    List<GroupEntity> findAllByFaculty_Id(Long faculty_id);

}
