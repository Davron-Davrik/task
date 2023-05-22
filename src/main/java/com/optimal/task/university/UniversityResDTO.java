package com.optimal.task.university;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversityResDTO {

    private Long id;
    private String name;
    private String address;
    private int openYear;
    private Timestamp createDate;
    private Timestamp updateDate;
}
