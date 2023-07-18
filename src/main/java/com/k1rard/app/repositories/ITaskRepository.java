package com.k1rard.app.repositories;

import com.k1rard.app.entities.Status;
import com.k1rard.app.entities.Task;

import java.util.List;
import java.util.Optional;

/**
 * @author k1rard
 * Interface represent the operation for tasks in the database.
 */
public interface ITaskRepository extends CrudRepository<Task, Long>{
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
