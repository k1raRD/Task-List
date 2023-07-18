package com.k1rard.app.repositories;
import java.util.List;

/**
 * @author k1rard
 * interface represents a simple CRUD in a generic manner.
 */
public interface CrudRepository<T, ID> {
    /**
     * Method that Look for all T records
     * @return a list of T
     */
    List<T> findAll();

    /**
     * Look for a single T record.
     * @param id Identifier of T by id.
     * @return T
     */
    T findById(ID id);

    /**
     * Save a T record.
     * @param t record to save
     * @return T record saved
     */
    T save(T t);

    /**
     * Method that update a T record
     * @param t record to save
     * @return T record Saved
     */
    T update(T t);

    /**
     * Method that delete a T record
     * @param t record to delete
     */
    void delete(T t);
}
