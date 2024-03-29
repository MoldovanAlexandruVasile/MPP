package ro.ubb.socket.common.Domain.Validator;

import ro.ubb.socket.common.Domain.Assign;

public class AssignValidator implements Validator<Assign> {
    @Override
    public void validate(Assign entity) throws ValidatorException {
        try {
            int ID = Integer.parseInt(entity.getId().toString());
            int PID = Integer.parseInt(entity.getPID());
            int SID = Integer.parseInt(entity.getSID());
        } catch (NumberFormatException e) {
            throw new ValidatorException("Wrong input detected !");
        }
    }
}