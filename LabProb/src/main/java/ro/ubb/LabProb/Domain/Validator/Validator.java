package ro.ubb.LabProb.Domain.Validator;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}