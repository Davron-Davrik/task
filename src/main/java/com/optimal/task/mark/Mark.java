package com.optimal.task.mark;


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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;
    
    @ManyToOne
    private Users student;

    @ManyToOne
    private Journal journal;

    @ManyToOne
    private Subject subject;


    public Mark(int score, Users student, Journal journal, Subject subject) {
        this.score = score;
        this.student = student;
        this.journal = journal;
        this.subject = subject;
    }
}
