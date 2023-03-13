package org.task;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.task.resources.TaskList;
import org.task.resources.TaskResource;


public class TaskApplication extends Application<TaskConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TaskApplication().run(args);
    }



    @Override
    public void run(TaskConfiguration configuration, Environment environment) throws Exception {
        TaskList taskList=new TaskList();
        TaskResource taskResource=new TaskResource(taskList);
        environment.jersey().register(taskResource);
        environment.healthChecks().register("Sheena", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }
        });


    }

}
