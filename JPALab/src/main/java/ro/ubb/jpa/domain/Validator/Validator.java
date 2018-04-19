package ro.ubb.jpa.domain.Validator;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}