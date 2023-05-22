package com.optimal.task.group;


import com.optimal.task.faculty.Faculty;
import com.optimal.task.journal.Journal;
import com.optimal.task.subject.Subject;
import com.optimal.task.users.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
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

    @Column(unique = true)
    private String name;
    private Integer year;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @OneToMany(mappedBy = "groupEntity")
    private List<Users> studentList;

    @ManyToOne
    private Faculty faculty;


    @OneToOne(mappedBy = "groupEntity")
    private Journal journal;

    @OneToMany(mappedBy = "groupEntity")
    private List<Subject> subjectList;

    public GroupEntity(String name, Integer year, Faculty faculty) {
        this.name = name;
        this.year = year;
        this.faculty = faculty;
    }
}
