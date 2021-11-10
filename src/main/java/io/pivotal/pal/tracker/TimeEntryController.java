package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<TimeEntry>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry foundTimeEntry = timeEntryRepository.find(id);
        if (null == foundTimeEntry) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(foundTimeEntry);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(timeEntryRepository.list());
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(id, timeEntry);
        if (null == updatedTimeEntry) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedTimeEntry);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
