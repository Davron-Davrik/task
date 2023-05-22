package com.optimal.task.controller;


import com.optimal.task.message.StateMessage;
import com.optimal.task.subject.ISubjectService;
import com.optimal.task.subject.SubjectDTO;
import com.optimal.task.subject.SubjectResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final ISubjectService iSubjectService;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody SubjectDTO dto) {
        try {

            StateMessage message = iSubjectService.add(dto);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody SubjectDTO dto) {
        try {

            StateMessage message = iSubjectService.edit(dto, id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {

            StateMessage message = iSubjectService.delete(id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/byStudentId/{id}")
    public ResponseEntity<?> byStudentId(@PathVariable Long id) {
        try {

            List<SubjectResDTO> list = iSubjectService.getAllByStudentId(id);

            return ResponseEntity.status(200).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }
}
