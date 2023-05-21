package com.optimal.task.group;


import com.optimal.task.faculty.Faculty;
import com.optimal.task.journal.Journal;
import com.optimal.task.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer year;

    @OneToMany(mappedBy = "groupEntity")
    private List<Student> studentList;

    @ManyToOne
    private Faculty faculty;


    @OneToOne(mappedBy = "groupEntity")
    private Journal journal;

}
