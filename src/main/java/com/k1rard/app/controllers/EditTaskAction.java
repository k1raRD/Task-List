package com.k1rard.app.controllers;

import com.k1rard.app.entities.Task;
import com.k1rard.app.entities.TaskNotFoundException;
import com.k1rard.app.services.ITaskService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class EditTaskAction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;

    @Inject
    transient FacesContext facesContext;

    @Inject
    private ITaskService service;

    private Long taskId;

    private Task task;

    public void init(){
        log.log(Level.INFO, "get task of id @{0}", taskId);

        if(taskId == null) {
            task = new Task();
        }else{
            task = service.findOptionalById(taskId)
                    .orElseThrow(() -> new TaskNotFoundException(taskId));
        }
    }

    public String save() {
        log.log(Level.INFO, "Saving task @{0}", task);
        if(task.getId() == null){
            this.task = service.save(task);
        } else{
            this.task = service.update(task);
        }
        FacesMessage info = new FacesMessage("Task is saved successfully!");
        facesContext.addMessage(null, info);

        return "/tasks.xhtml?faces-redirect=true";
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

    public void setTask(Task task) {
        this.task = task;
    }
}
