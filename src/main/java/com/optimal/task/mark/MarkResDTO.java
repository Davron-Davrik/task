package com.optimal.task.mark;


import com.optimal.task.subject.SubjectResDTO;
import com.optimal.task.users.UsersResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarkResDTO {

    private Long id;
    private int score;
    private Timestamp createDate;
    private Timestamp updateDate;
    private UsersResDTO student;
    private SubjectResDTO subject;
}
