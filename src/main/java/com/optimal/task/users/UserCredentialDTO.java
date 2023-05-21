package com.optimal.task.users;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UserCredentialDTO {

//    @Length(max = 25, min = 4)
    private String username;

    @Length(min = 8)
    @NotNull
    private String oldPassword;
    @Length(min = 8)
    private String newPassword1;
    @Length( min = 8)
    private String newPassword2;
}
