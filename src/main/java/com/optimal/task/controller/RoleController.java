package com.optimal.task.controller;

import com.optimal.task.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.attribute.UserPrincipalNotFoundException;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/role")
public class RoleController {


    private final RoleService roleService;



    @GetMapping
    @PreAuthorize("hasAnyRole('XODIMLAR_BO`LIMI','ADMIN')")
    public ResponseEntity<?> getAll(@RequestParam("type") String type, HttpServletRequest request) throws UserPrincipalNotFoundException {
        try {
            return ResponseEntity.ok(roleService.getRoll(request,type));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());

        }
    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('BOSHQARMA_BUXGALTE','BO`LIM_BUXGALTER')")
    public ResponseEntity<?> getAll(HttpServletRequest request) throws UserPrincipalNotFoundException {

        try {
            return ResponseEntity.ok(roleService.getRoll(request));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());

        }
    }



    @GetMapping(("/department"))
    @PreAuthorize("hasAnyRole('XODIMLAR_BO`LIMI','ADMIN')")
    public ResponseEntity<?> getAllByDepartment() {

        try {
            return ResponseEntity.ok(roleService.getRolesByDepartments());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());

        }
    }



    @GetMapping(("/kindergarten"))
    @PreAuthorize("hasAnyRole('XODIMLAR_BO`LIMI','ADMIN')")
    public ResponseEntity<?> getAllByKindergarten() {
        return ResponseEntity.ok(roleService.getRolesByKindergarten());
    }
}
