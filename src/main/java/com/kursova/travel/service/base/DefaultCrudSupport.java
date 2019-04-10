package com.kursova.travel.service.base;

import com.google.common.collect.Lists;
import com.kursova.travel.common.PermissionChecker;
import com.kursova.travel.entity.base.AbstractIdentifiable;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

/**
 * Default implementation of {@link CrudSupport} which simply delegates
 * CRUD operations to {@link CrudRepository}.
 */
@AllArgsConstructor
public abstract class DefaultCrudSupport<E extends AbstractIdentifiable> implements CrudSupport<E> {

    private JpaRepository<E, Long> repository;

    @Override
    public Optional<E> findById(Long entityId) {
        return repository.findById(entityId);
    }

    @Override
    public E getById(Long entityId) {
        return repository
                .findById(entityId)
                .orElseThrow(() -> new EmptyResultDataAccessException("Entity was not found by ID: " + entityId, 1));
    }

    @Override
    public List<E> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<E> findAll(Collection<Long> ids) {
        return Lists.newArrayList(repository.findAllById(ids));
    }

    @Override
    public List<E> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public E update(E entity) {
        Objects.requireNonNull(entity.getId(), "Could not update entity. Entity hasn't persisted yet");
        return repository.save(entity);
    }

    @Override
    public E create(E entity) {
        PermissionChecker.check(
                Objects.isNull(entity.getId()),
                "Could not create entity. Entity has already exists",
                IllegalArgumentException::new
        );
        return repository.save(entity);
    }

    @Override
    public E save(E entity) {
        return isNull(entity.getId()) ? create(entity) : update(entity);
    }

    @Override
    public void createAll(Collection<E> entities) {
        repository.saveAll(entities);
    }

    /**
     * {@inheritDoc}
     * Do not override this method!
     */
    @Override
    public void delete(E entity) {
        Objects.requireNonNull(entity.getId(), "Could not delete entity. Entity hasn't persisted yet");
        repository.delete(entity);
    }

    /**
     * {@inheritDoc}
     * Do not override this method!
     */
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
