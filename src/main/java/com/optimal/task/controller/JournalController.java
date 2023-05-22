package com.optimal.task.controller;


import com.optimal.task.journal.IJournalService;
import com.optimal.task.journal.JournalDTO;
import com.optimal.task.message.StateMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/journal")
public class JournalController {

    private final IJournalService iJournalService;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody JournalDTO dto) {
        try {

            StateMessage message = iJournalService.add(dto);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody JournalDTO dto) {
        try {

            StateMessage message = iJournalService.edit(dto, id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {

            StateMessage message = iJournalService.delete(id);
            return ResponseEntity.status(message.getCode()).body(message);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(e.hashCode()).body(e.getMessage());
        }
    }
}
