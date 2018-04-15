package ro.ubb.socket.common.Domain.Validator;

import ro.ubb.socket.common.Domain.Student;

import java.io.IOException;

public class StudentValidator implements Validator<Student> {
    @Override
    public void validate(Student entity) throws ValidatorException {
        try {
            int ID = Integer.parseInt(entity.getId().toString());
            int serialNumber = Integer.parseInt(entity.getSerialNumber());
            String name = entity.getName();
            if (name.matches("[0-9]+"))
                throw new IOException("");
        } catch (NumberFormatException | IOException e) {
            throw new ValidatorException("Wrong input detected !");
        }
    }
}