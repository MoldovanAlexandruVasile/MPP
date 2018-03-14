package ro.ubb.LabProb.Repository;

import ro.ubb.LabProb.Domain.Assign;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AssignFileRepository extends InMemoryRepository<Long, Assign> {
    private String fileName;

    public AssignFileRepository(Validator<Assign> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line ->
            {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                String SID = items.get(1);
                String PID = items.get(2);

                Assign problem = new Assign(SID, PID);
                problem.setId(id);

                try {
                    super.save(problem);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Assign> save(Assign entity) throws ValidatorException {
        Optional<Assign> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    @Override
    public Optional<Assign> delete(Long id) throws ValidatorException {
        Optional<Assign> op = super.findOne(id);
        Assign asg = op.get();
        Optional<Assign> optional = super.delete(id);
        if (!optional.isPresent()) {
            return optional;
        }
        deleteFromFile(asg);
        return Optional.empty();
    }

    @Override
    public Optional<Assign> update(Assign entity) throws ValidatorException {
        Optional<Assign> optional = super.findOne(entity.getId());
        if (!optional.isPresent()) {
            return optional;
        }
        deleteFromFile(entity);
        saveToFile(entity);
        super.update(entity);
        return Optional.empty();
    }

    private void saveToFile(Assign entity) {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.newLine();
            bufferedWriter.write(entity.getId() + "," + entity.getSID() + "," + entity.getPID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFromFile(Assign asg) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path path = Paths.get(fileName);
        super.findAll().forEach(assign -> {
            if (assign.getId() != asg.getId()) {
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                    bufferedWriter.newLine();
                    bufferedWriter.write(assign.getId() + "," + assign.getSID() + "," + assign.getPID());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}