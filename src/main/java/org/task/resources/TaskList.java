package org.task.resources;
import java.util.*;

public class TaskList {
    private Map<String, TaskItem> items=new HashMap<>();

    public List<TaskItem> getAll() {
        return new ArrayList<>(items.values());
    }
    public TaskItem get(String id) {
        return items.get(id);
    }
    public void add(TaskItem item) {
        item.setId(UUID.randomUUID().toString());
        items.put(item.getId(),item);
    }
    public void update(String id,TaskItem item) {
        item.setId(id);
        items.put(id,item);
    }
    public void delete(String id)
    {
        items.remove(id);
    }
}
