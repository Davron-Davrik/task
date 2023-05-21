package com.optimal.task.faculty;


import com.optimal.task.group.GroupEntity;
import com.optimal.task.university.University;
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
public class Faculty {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @OneToMany(mappedBy = "faculty")
    private List<GroupEntity> groupEntityList;

    @ManyToOne
    private University university;
}
