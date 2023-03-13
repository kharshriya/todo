package org.task.resources;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
    private TaskList taskList;

    public TaskResource(TaskList taskList) {
        this.taskList=taskList;
    }

    @GET
    public List<TaskItem> getAll() {
        return taskList.getAll();
    }

    @GET
    @Path("/{id}")
    public TaskItem get(@PathParam("id") String id) {
        return taskList.get(id);
    }

    @POST
    public TaskItem add(TaskItem item) {
        taskList.add(item);
        return item;
    }
    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, TaskItem item) {
        taskList.update(id, item);
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void partialUpdate(@PathParam("id") String id, Map<String,Object> updates) {
        TaskItem item=taskList.get(id);
        if (item ==null) {
            throw new NotFoundException();
        }
        for (Map.Entry<String, Object> entry: updates.entrySet()) {
            String field =entry.getKey();
            Object value=entry.getValue();
            switch(field) {
                case "title":
                    item.setTitle((String) value);
                    break;
                case "completed":
                    item.setCompleted((Boolean) value);
                    break;
                default:
                    throw new BadRequestException("Invalid field: "+ field);
            }
        }
        taskList.update(id,item);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        taskList.delete(id);
    }
}
