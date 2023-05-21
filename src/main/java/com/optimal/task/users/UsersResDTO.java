package com.optimal.task.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersResDTO {

    private Integer id;
    private String name;
    private String fatherName;
    private String surname;
    private boolean status;
    private boolean delete;
    private String username;
    private String phoneNumber;
    private String role;
    private String positionName;
    private String departmentName;
    private Integer departmentId;
    private String regionalDepartmentName;
    private Integer regionalDepartmentId;
    private String kindergartenName;
    private Integer kindergartenId;
    private int notification;
    private boolean permissionState;
}
