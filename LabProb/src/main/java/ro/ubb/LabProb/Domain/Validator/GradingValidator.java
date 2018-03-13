package ro.ubb.LabProb.Domain.Validator;

import ro.ubb.LabProb.Domain.Grading;

public class GradingValidator implements Validator<Grading> {
    @Override
    public void validate(Grading entity) throws ValidatorException
    {
        try
        {
            int id = Integer.parseInt(entity.getId().toString());
            int aid = Integer.parseInt(entity.getAID());
            int grade = entity.getGrade();
        }
        catch(Exception ex)
        {
            throw new ValidatorException("Wrong input detected!");
        }
    }
}
