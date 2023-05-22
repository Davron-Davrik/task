package com.optimal.task.faculty;


import com.optimal.task.group.GroupEntity;
import com.optimal.task.university.University;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacultyResDTO {

    private Long id;
    private String name;
    private Timestamp createDate;
    private Timestamp updateDate;
    private List<GroupEntity> groupEntityList;
    private University university;
}
