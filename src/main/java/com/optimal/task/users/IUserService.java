package com.optimal.task.users;

import com.optimal.task.message.StateMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {

    Users parseToken(HttpServletRequest httpServletRequest);

    StateMessage addStudent(UsersDTO dto);

    StateMessage editStudent(UsersDTO dto, Long id);

    StateMessage deleteStudent(Long id);

    List<UsersResDTO> searchByName(String name);

    List<UsersResDTO> getAllByGroupId(Long groupId);

    UsersResDTO parseToDTO(Users users);
}
