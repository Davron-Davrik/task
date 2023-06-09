package com.optimal.task.controller;


import com.optimal.task.message.StateMessage;
import com.optimal.task.users.IUserService;
import com.optimal.task.users.UsersDTO;
import com.optimal.task.users.UsersResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {


    private final IUserService iUserService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody UsersDTO dto) {
        try {

            StateMessage message = iUserService.addStudent(dto);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody UsersDTO dto) {
        try {

            StateMessage message = iUserService.editStudent(dto, id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {

            StateMessage message = iUserService.deleteStudent(id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("name") String name) {
        try {

            List<UsersResDTO> list = iUserService.searchByName(name);

            return ResponseEntity.status(200).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getAllByGroupId/{id}")
    public ResponseEntity<?> getAll(@PathVariable Long id) {
        try {

            List<UsersResDTO> list = iUserService.getAllByGroupId(id);

            return ResponseEntity.status(200).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }
}
