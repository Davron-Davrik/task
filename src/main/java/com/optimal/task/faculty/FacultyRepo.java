package com.optimal.task.faculty;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepo extends JpaRepository<Faculty, Long> {

    boolean existsAllByNameAndUniversity_Id(String name, Long university_id);

    List<Faculty> findAllByUniversity_Id(Long university_id);
}
