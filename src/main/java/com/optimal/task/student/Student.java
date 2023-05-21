package com.optimal.task.student;


import com.optimal.task.group.GroupEntity;
import com.optimal.task.mark.Mark;
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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToOne
    private GroupEntity groupEntity;


    @OneToMany(mappedBy = "student")
    private List<Mark> markList;

}
