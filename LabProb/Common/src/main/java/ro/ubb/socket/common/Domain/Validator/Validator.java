package ro.ubb.socket.common.Domain.Validator;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}