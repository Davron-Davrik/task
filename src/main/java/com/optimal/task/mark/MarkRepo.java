package com.optimal.task.mark;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepo extends JpaRepository<Mark,Long> {

    List<Mark> findAllByStudent_Id(Long student_id);
}
