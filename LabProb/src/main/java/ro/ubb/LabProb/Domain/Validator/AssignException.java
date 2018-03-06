package ro.ubb.LabProb.Domain.Validator;

public class AssignException extends RuntimeException
{
    public AssignException(String message) { super(message); }

    public AssignException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssignException(Throwable cause) {
        super(cause);
    }
}