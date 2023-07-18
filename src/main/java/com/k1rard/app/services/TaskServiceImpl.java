package com.k1rard.app.services;

import com.k1rard.app.entities.Status;
import com.k1rard.app.entities.Task;
import com.k1rard.app.repositories.ITaskRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Stateless
public class TaskServiceImpl implements ITaskService {

    @Inject
    private ITaskRepository repository;


    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Task save(Task task) {
        task.setCreatedDate(LocalDateTime.now());
        task.setLastModifiedDate(LocalDateTime.now());
        return repository.save(task);
    }

    @Override
    public Task update(Task task) {
        task.setLastModifiedDate(LocalDateTime.now());
        return repository.update(task);
    }

    @Override
    public void delete(Task task) {
        repository.delete(task);
    }

    @Override
    public Optional<Task> findOptionalById(Long id) {
        return repository.findOptionalById(id);
    }

    @Override
    public List<Task> findByStatus(Status status) {
        return repository.findByStatus(status);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
