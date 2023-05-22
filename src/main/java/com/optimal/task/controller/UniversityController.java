package com.optimal.task.controller;


import com.optimal.task.message.StateMessage;
import com.optimal.task.university.IUniversityService;
import com.optimal.task.university.UniversityDTO;
import com.optimal.task.university.UniversityResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/university")
public class UniversityController {

    private final IUniversityService iUniversityService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody UniversityDTO dto) {
        try {

            StateMessage message = iUniversityService.add(dto);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody UniversityDTO dto) {
        try {

            StateMessage message = iUniversityService.edit(dto, id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {

            StateMessage message = iUniversityService.delete(id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {

            UniversityResDTO dto = iUniversityService.getOne(id);
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

            List<UniversityResDTO> list = iUniversityService.getAll();

            return ResponseEntity.status(200).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }
}
