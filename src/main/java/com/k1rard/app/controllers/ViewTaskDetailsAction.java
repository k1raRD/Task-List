package com.k1rard.app.controllers;

import com.k1rard.app.entities.Task;
import com.k1rard.app.entities.TaskNotFoundException;
import com.k1rard.app.services.ITaskService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("viewTaskAction")
@ViewScoped
public class ViewTaskDetailsAction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;

    @Inject
    private ITaskService service;

    @NotNull
    private Long taskId;

    private Task task;

    public void init(){
        log.log(Level.INFO, "get task details of id @{id}", taskId);
        task = service.findOptionalById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Task getTask() {
        return task;
    }
}
