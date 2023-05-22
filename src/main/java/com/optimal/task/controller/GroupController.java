package com.optimal.task.controller;


import com.optimal.task.group.GroupDTO;
import com.optimal.task.group.GroupResDTO;
import com.optimal.task.group.IGroupService;
import com.optimal.task.message.StateMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final IGroupService iGroupService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody GroupDTO dto) {
        try {

            StateMessage message = iGroupService.add(dto);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody GroupDTO dto) {
        try {

            StateMessage message = iGroupService.edit(dto, id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {

            StateMessage message = iGroupService.delete(id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {

            GroupResDTO dto = iGroupService.getOne(id);
            if (dto == null) {
                StateMessage message = new StateMessage().wrongInformation();
                return ResponseEntity.status(message.getCode()).body(message);
            }
            return ResponseEntity.status(200).body(dto);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<?> getAll() {
        try {

            List<GroupResDTO> list = iGroupService.getAll();

            return ResponseEntity.status(200).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/byFacultyId/{id}")
    public ResponseEntity<?> getAllByFacultyId(@PathVariable Long id) {
        try {

            List<GroupResDTO> list = iGroupService.getAll(id);

            return ResponseEntity.status(200).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }
}
