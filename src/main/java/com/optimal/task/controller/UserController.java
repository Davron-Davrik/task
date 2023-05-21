package com.optimal.task.controller;


import com.optimal.task.message.StateMessage;
import com.optimal.task.users.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAnyRole('ADMIN','XODIMLAR_BO`LIMI','SUPER_ADMIN','ADMIN_DEPARTMENT','BOSHQARMA_BUXGALTER','TEXNOLOG','BO`LIM_BUXGALTER','HAMSHIRA','OMBORCHI','RAXBAR','BUXGALTER')")
    @PutMapping("credentials/password")
    public ResponseEntity<?> editPassword(@RequestBody @Valid UserCredentialDTO dto, HttpServletRequest request, Errors errors) {
        try {
            if (errors.hasErrors())
                return ResponseEntity.status(400).body(errors.toString());
            StateMessage message = userService.changeCredentialsPassword(request,dto);
            return ResponseEntity.status(message.getCode()).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }


    @PreAuthorize("hasAnyRole('ADMIN','XODIMLAR_BO`LIMI')")
    @PutMapping("reset/password/{userId}")
    public ResponseEntity<?> resetUserCredentials(@PathVariable Integer userId, HttpServletRequest request) {
        try {
            StateMessage message = userService.resetUserCredentials(userId);
            return ResponseEntity.status(message.getCode()).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }
}
