package com.optimal.task.subject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject,Long> {

    boolean existsByNameAndGroupEntity_Id(String name, Long groupEntity_id);

    List<Subject> findAllByGroupEntity_Id(Long groupEntity_id);
}
