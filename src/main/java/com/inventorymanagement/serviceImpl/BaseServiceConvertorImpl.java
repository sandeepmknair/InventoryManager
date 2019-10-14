package com.inventorymanagement.serviceImpl;

import com.inventorymanagement.service.BaseService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
public abstract class BaseServiceConvertorImpl<SOURCE, TARGET> implements BaseService<SOURCE> {

    private final Converter<SOURCE, TARGET> sourceConverter;
    private final Converter<TARGET, SOURCE> destinationConverter;
    private final BaseService<TARGET> baseService;

    public BaseServiceConvertorImpl(final JpaRepository<TARGET, Long> jpaRepository,
                                    final Converter<SOURCE, TARGET> sourceConverter,
                                    final Converter<TARGET, SOURCE> destinationConverter) {
        BiFunction<Long, SOURCE, TARGET> buildPersist = this::buildToPersistObject;
        this.baseService = new BaseServiceImpl<TARGET>(jpaRepository) {
            @Override
            protected TARGET buildToPersistObject(Long id, TARGET entityObject) {
                return buildPersist.apply(id, destinationConverter.convert(entityObject));
            }
        };
        this.sourceConverter = sourceConverter;
        this.destinationConverter = destinationConverter;
    }

    @Override
    public Long getCount() {
        return baseService.getCount();
    }

    @Override
    public Optional<SOURCE> findById(Long id) {
        return baseService.findById(id).map(destinationConverter::convert);
    }

    @Override
    public List<SOURCE> findAll() {
        return baseService.findAll().stream().map(destinationConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Optional<SOURCE> save(SOURCE entityType) {
        TARGET target = baseService.save(sourceConverter.convert(entityType)).get();
        return Optional.of(target).map(destinationConverter::convert);
    }

    @Override
    public void delete(Long id) {
        baseService.delete(id);
    }

    @Override
    public Optional<SOURCE> update(Long id, SOURCE entityType) {
        return baseService.update(id, buildToPersistObject(id, entityType)).map(destinationConverter::convert);
    }

    protected abstract TARGET buildToPersistObject(final Long id, final SOURCE entityObject);
}
