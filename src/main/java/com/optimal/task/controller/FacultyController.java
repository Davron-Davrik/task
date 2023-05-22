package com.optimal.task.controller;


import com.optimal.task.faculty.FacultyDTO;
import com.optimal.task.faculty.FacultyResDTO;
import com.optimal.task.faculty.IFacultyService;
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
@RequestMapping("/api/faculty")
public class FacultyController {

    private final IFacultyService iFacultyService;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody FacultyDTO dto) {
        try {

            StateMessage message = iFacultyService.add(dto);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody FacultyDTO dto) {
        try {

            StateMessage message = iFacultyService.edit(dto, id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {

            StateMessage message = iFacultyService.delete(id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {

            FacultyResDTO dto = iFacultyService.getOne(id);
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

            List<FacultyResDTO> list = iFacultyService.getAll();

            return ResponseEntity.status(200).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/byUniversityId/{id}")
    public ResponseEntity<?> byUniversityId(@PathVariable Long id) {
        try {

            List<FacultyResDTO> list = iFacultyService.getAll(id);

            return ResponseEntity.status(200).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }
}
