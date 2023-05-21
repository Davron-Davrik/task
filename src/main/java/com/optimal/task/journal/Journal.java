package com.optimal.task.journal;


import com.optimal.task.group.GroupEntity;
import com.optimal.task.mark.Mark;
import com.optimal.task.subject.Subject;
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
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @ManyToMany
    private List<Subject> subjectList;

    @OneToOne
    private GroupEntity groupEntity;

    @OneToMany(mappedBy = "journal")
    private List<Mark> markList;
}
