package ro.ubb.LabProb.Repository;

import ro.ubb.LabProb.Domain.Problem;
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

public class ProblemFileRepository extends InMemoryRepository<Long, Problem> {
    private String fileName;

    public ProblemFileRepository(Validator<Problem> validator, String fileName) {
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
                String description = items.get(1);

                Problem problem = new Problem(description);
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
    public Optional<Problem> save(Problem entity) throws ValidatorException {
        Optional<Problem> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    @Override
    public Optional<Problem> delete(Long id) throws ValidatorException {
        Optional<Problem> op = super.findOne(id);
        Problem prb = op.get();
        Optional<Problem> optional = super.delete(id);
        if (!optional.isPresent()) {
            return optional;
        }
        deleteFromFile(prb);
        return Optional.empty();
    }

    @Override
    public Optional<Problem> update(Problem entity) throws ValidatorException {
        Optional<Problem> optional = super.findOne(entity.getId());
        if (!optional.isPresent()) {
            return optional;
        }
        deleteFromFile(entity);
        saveToFile(entity);
        super.update(entity);
        return Optional.empty();
    }

    private void saveToFile(Problem entity) {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.newLine();
            bufferedWriter.write(entity.getId() + "," + entity.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFromFile(Problem std) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path path = Paths.get(fileName);
        super.findAll().forEach(problem -> {
            if (!problem.equals(std)) {
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                    bufferedWriter.newLine();
                    bufferedWriter.write(problem.getId() + "," + problem.getDescription());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}