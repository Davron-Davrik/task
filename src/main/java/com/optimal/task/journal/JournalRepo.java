package com.optimal.task.journal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JournalRepo extends JpaRepository<Journal,Long> {

    boolean existsAllByNameAndGroupEntity_Id(String name, Long groupEntity_id);
}
