package ro.ubb.LabProb.Domain.Validator;

import ro.ubb.LabProb.Domain.Problem;

import java.io.IOException;

public class ProblemValidator implements Validator<Problem>
{
    @Override
    public void validate(Problem entity) throws ValidatorException
    {
        try
        {
            int ID = Integer.parseInt(entity.getId().toString());
            String description = entity.getDescription();
            if (description.matches("[0-9]+"))
                throw new IOException("");
        }
        catch(NumberFormatException | IOException e)
        {
            throw new ValidatorException("Wrong input detected !");
        }
    }
}