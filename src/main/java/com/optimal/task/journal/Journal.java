package com.optimal.task.journal;


import com.optimal.task.group.GroupEntity;
import com.optimal.task.mark.Mark;
import com.optimal.task.subject.Subject;
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
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    private List<Subject> subjectList;

    @OneToOne
    private GroupEntity groupEntity;

    @OneToMany(mappedBy = "journal")
    private List<Mark> markList;
}
