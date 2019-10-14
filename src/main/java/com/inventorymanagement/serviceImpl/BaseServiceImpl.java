package com.inventorymanagement.serviceImpl;

import com.inventorymanagement.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private final JpaRepository<T, Long> jpaRepository;

    public BaseServiceImpl(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public Long getCount() {
        return this.jpaRepository.count();
    }

    public Optional<T> findById(final Long id) {
        return Optional.of(this.jpaRepository.getOne(id));
    }

    public List<T> findAll() {
        return this.jpaRepository.findAll();
    }

    public void delete(final Long id) {
        this.jpaRepository.delete(jpaRepository.getOne(id));
    }

    public Optional<T> save(final T entityObject) {
        return Optional.of(jpaRepository.saveAndFlush(entityObject));
    }

    public Optional<T> update(final Long id, final T entityObject) {
        return Optional.of(jpaRepository.saveAndFlush(buildToPersistObject(id, entityObject)));
    }

    protected abstract T buildToPersistObject(final Long id, final T entityObject);
}
