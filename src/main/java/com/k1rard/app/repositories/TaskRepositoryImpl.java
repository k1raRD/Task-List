package com.k1rard.app.repositories;

import com.k1rard.app.entities.Status;
import com.k1rard.app.entities.Task;
import com.k1rard.app.entities.TaskNotFoundException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

/**
 * @author k1rard
 * Class that implements ITaskRepository
 */
@RequestScoped
public class TaskRepositoryImpl implements ITaskRepository{
    /**
     * Object that manage entities on the database
     */
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Task> findAll() {
        return em.createQuery("FROM Task", Task.class).getResultList();
    }

    @Override
    public Task findById(Long id) {
        Task task = em.find(Task.class, id);
        if(task == null ){
            throw new TaskNotFoundException(id);
        }
        return task;
    }

    @Override
    public Task save(Task task) {
        em.persist(task);
        return task;
    }

    @Override
    public Task update(Task task) {
        return em.merge(task);
    }

    @Override
    public void delete(Task task) {
        task = em.merge(task);
        em.remove(task);
    }

    @Override
    public Optional<Task> findOptionalById(Long id) {
        Task task = em.find(Task.class, id);
        return Optional.ofNullable(task);
    }

    @Override
    public List<Task> findByStatus(Status status) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        CriteriaQuery<Task> q = cb.createQuery(Task.class);
        Root<Task> c = q.from(Task.class);

        if(null != status){
            q.where(cb.equal(c.get("status"), status));
        }

        TypedQuery<Task> query = em.createQuery(q);

        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        em.remove(this.findById(id));
    }
}
