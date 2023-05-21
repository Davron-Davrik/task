package com.optimal.task.role;

import com.optimal.task.users.UserService;
import com.optimal.task.users.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;
    private final UserService userService;


    public List<Role> getRoll(HttpServletRequest request, String type) throws UserPrincipalNotFoundException {

        Users users = userService.parseToken(request);
        String roleName = users.getRoles().get(0).getName();


        if (roleName.equals(RoleType.ADMIN.getName())) {
            if (type.equals("region"))
                return roleRepo.findAllById(List.of(4, 5));
            if (type.equals("department"))
                return roleRepo.findAllById(List.of(6, 9, 11));
        } else if (roleName.equals(RoleType.HUMAN_RESOURCES.getName())) {
            if (type.equals("kindergarten"))
                return roleRepo.findAllById(List.of(7, 8, 10));
        } else {
            throw new UserPrincipalNotFoundException("notog'ri yo'lga murojat qildingiz");
        }
        return new ArrayList<>();
    }


    public List<RoleResDTO> getRoll(HttpServletRequest request) {

        Users users = userService.parseToken(request);

            List<Role> allById = roleRepo.findAll();
            List<RoleResDTO> list = new ArrayList<>();


            for (Role role : allById) {
                list.add(new RoleResDTO(role.getId(), role.getName(), role.getPositionName()));
            }

            return list;
    }

    public List<Role> getRolesByDepartments() {
        return roleRepo.findAllById(List.of(6, 9, 11));
    }

    public List<Role> getRolesByKindergarten() {
        return roleRepo.findAllById(List.of(7, 8, 10));
    }

}
