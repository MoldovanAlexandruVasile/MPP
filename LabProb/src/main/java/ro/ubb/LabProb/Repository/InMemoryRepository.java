package ro.ubb.LabProb.Repository;

import ro.ubb.socket.common.Domain.BaseEntity;
import ro.ubb.socket.common.Domain.Validator.Validator;
import ro.ubb.socket.common.Domain.Validator.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    private Map<ID, T> entities;
    private Validator<T> validator;

    public InMemoryRepository(Validator<T> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null !");
        }
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        try {
            if (entity == null) {
                throw new ValidatorException("ID must not be null !");
            }
            validator.validate(entity);

            Optional<T> op = this.findOne(entity.getId());
            if (op.isPresent()) {
                throw new ValidatorException("Object already exists !");
            }

            return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
        } catch (ValidatorException e) {
            throw new ValidatorException(e.getMessage());
        }
    }

    @Override
    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }

        Optional<T> op = this.findOne(id);
        if (!op.isPresent()) {
            throw new ValidatorException("Object does not exists !");
        }

        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        try {
            if (entity == null) {
                throw new ValidatorException("Entity must not be null");
            }

            validator.validate(entity);

            Optional<T> op = this.findOne(entity.getId());
            if (!op.isPresent()) {
                throw new ValidatorException("Object does not exists !");
            }
            return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));

        } catch (ValidatorException e) {
            throw new ValidatorException(e.getMessage());
        }
    }
}
