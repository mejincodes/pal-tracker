package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private final HashMap<Long, TimeEntry> timeEntries = new HashMap<Long, TimeEntry>();

    private long currentId = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        long id = this.currentId++;
        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        timeEntries.put(id, newTimeEntry);
        return newTimeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (null == find(id)) return null;
        TimeEntry updatedTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.put(id, updatedTimeEntry);
        return updatedTimeEntry;
    }

    public void delete(long id) {
        timeEntries.remove(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeEntries.values());
    }
}
