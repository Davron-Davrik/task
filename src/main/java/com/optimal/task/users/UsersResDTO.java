package com.optimal.task.users;

import com.optimal.task.faculty.FacultyResDTO;
import com.optimal.task.group.GroupResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersResDTO {

    private Long id;
    private String name;
    private String username;
    private GroupResDTO group;
    private Timestamp updateDate;
    private Timestamp createDate;
    private FacultyResDTO faculty;
    private int totalScore;
}
