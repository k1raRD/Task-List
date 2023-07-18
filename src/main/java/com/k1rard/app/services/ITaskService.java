package com.k1rard.app.services;

import com.k1rard.app.entities.Status;
import com.k1rard.app.entities.Task;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

/**
 * @author k1rard
 * Interface to represent the tasks service.
 */
@Local
public interface ITaskService {
    /**
     * Method that Look for all Task records
     * @return a list of Task
     */
    List<Task> findAll();
    /**
     * Look for a single Task record by id.
     * @param id Identifier of T
     * @return Task
     */
    Task findById(Long id);
    /**
     * Method that save a Task record.
     * @param task to save
     * @return Task saved
     */
    Task save(Task task);
    /**
     * Method that update a Task.
     * @param task to save
     * @return T record Saved
     */
    Task update(Task task);

    /**
     * Method that delete a Task record
     * @param task to delete
     */
    void delete(Task task);

    /**
     * Method that find a Task record by id.
     * @param id Identifier of Task record.
     * @return Optional<Task>
     */
    Optional<Task> findOptionalById(Long id);
    /**
     * Method that find all record by Status
     * @param status of records to find
     * @return List<Task>
     */
    List<Task> findByStatus(Status status);
    /**
     * Method that delete a task by id
     * @param id Identifier of Task to delete.
     */
    void deleteById(Long id);
}
