package com.kursova.travel.service.base;

import com.kursova.travel.entity.base.AbstractIdentifiable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Defines commonly used CRUD operations
 */
public interface CrudSupport<E extends AbstractIdentifiable> {

    /**
     * Retrieves an entity by its userId.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given userId or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code userId} is {@literal null}.
     */
    Optional<E> findById(final Long entityId);

    /**
     * Retrieves an entity by its userId.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given userId
     * @throws org.springframework.dao.EmptyResultDataAccessException if entity is not found.
     */
    E getById(final Long entityId);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    List<E> findAll();

    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @param pageable offsets and limits
     * @return a page of entities
     */
    Page<E> findAll(Pageable pageable);

    /**
     * Returns all instances of entities meeting the sort restriction provided in the {@code sort} object.
     *
     * @param sort sorting rules
     * @return a collection of entities
     */
    List<E> findAll(Sort sort);

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids preferable implementation is {@link java.util.Set}
     * @return {@link List <E>} collection of entities
     */
    List<E> findAll(Collection<Long> ids);

    /**
     * Updates an entity.
     * If you want to use one method for <strong>create</strong> and <strong>update</strong>
     * operations use {@link #save(AbstractIdentifiable)} instead.
     *
     * @param entity entity to update
     * @return updated entity
     * @throws IllegalArgumentException if entity hasn't persisted yet
     */
    E update(final E entity);

    /**
     * Persists an entity.
     *
     * @param entity entity to create
     * @return created entity
     * @throws IllegalArgumentException if entity has already persisted
     */
    E create(final E entity);

    /**
     * Saves an entity.
     * This method resolves entity's state and
     * delegates to either {@link #create(AbstractIdentifiable)} or {@link #update(AbstractIdentifiable)}
     * method to do actual work.
     *
     * @param entity entity to save
     * @return saved entity
     */
    E save(final E entity);

    /**
     * Create entities.
     *
     * @param entities collection of entities to save
     */
    void createAll(final Collection<E> entities);

    /**
     * Removes an entity.
     *
     * @param entity entity to remove
     * @throws IllegalArgumentException if entity hasn't persisted yet
     */
    void delete(final E entity);

    /**
     * Removes an entity by it's identifier.
     *
     * @param id unique identifier
     */
    void delete(final Long id);
}
