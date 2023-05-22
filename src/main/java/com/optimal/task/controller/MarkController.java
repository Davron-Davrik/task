package com.optimal.task.controller;


import com.optimal.task.mark.IMarkService;
import com.optimal.task.mark.MarkDTO;
import com.optimal.task.mark.MarkResDTO;
import com.optimal.task.message.StateMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mark")
public class MarkController {

    private final IMarkService iMarkService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody MarkDTO dto) {
        try {

            StateMessage message = iMarkService.add(dto);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody MarkDTO dto) {
        try {

            StateMessage message = iMarkService.edit(dto, id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getAllByStudentId/{id}")
    public ResponseEntity<?> getAllByStudentId(@PathVariable Long id) {
        try {

            List<MarkResDTO> list = iMarkService.getAllByStudentId(id);

            return ResponseEntity.status(200).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }
}
