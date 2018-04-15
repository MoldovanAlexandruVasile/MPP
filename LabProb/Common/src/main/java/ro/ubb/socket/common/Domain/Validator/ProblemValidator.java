package ro.ubb.socket.common.Domain.Validator;

import ro.ubb.socket.common.Domain.Problem;

public class ProblemValidator implements Validator<Problem> {
    @Override
    public void validate(Problem entity) throws ValidatorException {
        try {
            int ID = Integer.parseInt(entity.getId().toString());
            String description = entity.getDescription();
        } catch (NumberFormatException e) {
            throw new ValidatorException("Wrong input detected !");
        }
    }
}