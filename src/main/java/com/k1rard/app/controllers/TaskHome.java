package com.k1rard.app.controllers;

import com.k1rard.app.entities.Status;
import com.k1rard.app.entities.Task;
import com.k1rard.app.entities.TaskNotFoundException;
import com.k1rard.app.services.ITaskService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class TaskHome implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;

    @Inject
    transient FacesContext facesContext;

    @Inject
    private ITaskService service;

    private List<TaskDetails> todoTasks = new ArrayList<>();
    private List<TaskDetails> doingTasks = new ArrayList<>();
    private List<TaskDetails> doneTasks = new ArrayList<>();

    @PostConstruct
    public void init(){
        log.log(Level.INFO, "Initializing TaskHome...");
        retrieveAllTasks();
    }

    private void retrieveAllTasks() {
        log.log(Level.INFO, "retrieving all tasks...");
        this.todoTasks = findTasksByStatus(Status.TODO);
        this.doingTasks = findTasksByStatus(Status.DOING);
        this.doneTasks = findTasksByStatus(Status.DONE);
    }

    private List<TaskDetails> findTasksByStatus(Status status){
        List<TaskDetails> taskList = new ArrayList<>();
        List<Task> tasks = service.findByStatus(status);

        tasks.stream().map(task -> {
            TaskDetails details = new TaskDetails();
            details.setId(task.getId());
            details.setName(task.getName());
            details.setDescription(task.getDescription());
            details.setCreatedDate(task.getCreatedDate());
            details.setLastModifiedDate(task.getLastModifiedDate());
            return details;
        }).forEach(taskList::add);

        return taskList;
    }

    public void deleteTask(Long id){
        log.log(Level.INFO, "delete task of id @{0}", id);

        Task task = service.findOptionalById(id)
                .orElseThrow( () -> new TaskNotFoundException(id));
        service.delete(task);

        retrieveAllTasks();

        FacesMessage deleteInfo = new FacesMessage(FacesMessage.SEVERITY_WARN, "Task is deleted!", "Task is deleted");
        facesContext.addMessage(null, deleteInfo);
    }

    public void markTaskDoing(Long id){
        log.log(Level.INFO, "Changing task DOING @{0}", id);

        Task task = service.findOptionalById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(Status.DOING);
        service.update(task);

        retrieveAllTasks();
    }

    public void markTaskDone(Long id){
        log.log(Level.INFO, "Changing task done @{0}", id);

        Task task = service.findOptionalById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(Status.DONE);
        service.update(task);

        retrieveAllTasks();
    }

    public List<TaskDetails> getTodoTasks() {
        return todoTasks;
    }

    public void setTodoTasks(List<TaskDetails> todoTasks) {
        this.todoTasks = todoTasks;
    }

    public List<TaskDetails> getDoingTasks() {
        return doingTasks;
    }

    public void setDoingTasks(List<TaskDetails> doingTasks) {
        this.doingTasks = doingTasks;
    }

    public List<TaskDetails> getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(List<TaskDetails> doneTasks) {
        this.doneTasks = doneTasks;
    }
}
