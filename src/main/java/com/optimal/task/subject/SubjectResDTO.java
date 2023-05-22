package com.optimal.task.subject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResDTO {

    private Long id;
    private String name;
    private Timestamp createDate;
    private Timestamp updateDate;
}
