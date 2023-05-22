package com.optimal.task.group;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupResDTO {
    private Long id;
    private String name;
    private Integer year;
    private Timestamp createDate;
    private Timestamp updateDate;
}
