package com.optimal.task.users;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    @Size(max = 25, min = 3)
    @NotNull
    private String name;

    @Size(max = 25, min = 3)
    @NotNull
    private String fatherName;

    @Size(max = 25, min = 3)
    @NotNull
    private String surname;

    @Size(max = 25, min = 4)
    @NotNull
    private String username;

    @Size(min = 8)
    private String password;

    @Size(max = 9, min = 9)
    @NotNull
    private String phoneNumber;

    @NotNull
    private Integer roleId;
    private boolean status;
}
