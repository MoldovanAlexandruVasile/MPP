package ro.ubb.LabProb.Repository;

import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StudentFileRepository extends InMemoryRepository<Long, Student>
{
    private String fileName;

    public StudentFileRepository(Validator<Student> validator, String fileName)
    {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData()
    {
        Path path = Paths.get(fileName);

        try
        {
            Files.lines(path).forEach(line ->
            {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                String serialNumber = items.get(1);
                String name = items.get((2));

                Student student = new Student(serialNumber, name);
                student.setId(id);

                try
                {
                    super.save(student);
                }
                catch (ValidatorException e)
                {
                    e.printStackTrace();
                }
            });
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Student> save(Student entity) throws ValidatorException
    {
        Optional<Student> optional = super.save(entity);
        if (optional.isPresent()) { return optional; }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Student entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND))
        {
            bufferedWriter.write(entity.getId() + "," + entity.getSerialNumber() + "," + entity.getName());
            bufferedWriter.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
