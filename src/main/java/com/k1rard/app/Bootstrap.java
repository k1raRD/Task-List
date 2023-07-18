package com.k1rard.app;

import com.k1rard.app.entities.Status;
import com.k1rard.app.entities.Task;
import com.k1rard.app.services.ITaskService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

//@Startup
@Singleton
public class Bootstrap implements IBootstrap{

    @Inject
    Logger log;

    @Inject
    ITaskService service;

    public Bootstrap() {
    }

    @PostConstruct
    public void init() {
        log.log(Level.INFO, "bootstraping application...");

        Stream.of("first", "second")
                .map(s -> {
                    Task task = new Task();
                    task.setName("My " + s + " task");
                    task.setDescription("The description of my " + s + " task");
                    task.setStatus(Status.TODO);
                    return task;
                })
                .map(data -> service.save(data))
                .toList()
                .forEach(task -> log.log(Level.INFO, " task saved: " +
                        "{0}", new Object[]{task}));
    }
}
