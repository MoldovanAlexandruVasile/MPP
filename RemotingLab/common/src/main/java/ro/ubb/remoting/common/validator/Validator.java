package ro.ubb.remoting.common.validator;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}