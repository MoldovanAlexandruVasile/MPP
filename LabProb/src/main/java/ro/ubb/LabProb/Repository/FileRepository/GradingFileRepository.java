package ro.ubb.LabProb.Repository.FileRepository;

import ro.ubb.LabProb.Domain.Grading;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.InMemoryRepository;

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

public class GradingFileRepository extends InMemoryRepository<Long, Grading> {
    private String fileName;

    public GradingFileRepository(Validator<Grading> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line ->
            {
                if (!line.isEmpty()) {
                    List<String> items = Arrays.asList(line.split(","));

                    Long id = Long.valueOf(items.get(0));
                    String AID = items.get(1);
                    Integer gr = Integer.valueOf(items.get(2));

                    Grading problem = new Grading(AID, gr);
                    problem.setId(id);

                    try {
                        super.save(problem);
                    } catch (ValidatorException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Grading> save(Grading entity) throws ValidatorException {
        Optional<Grading> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    @Override
    public Optional<Grading> delete(Long id) throws ValidatorException {
        Optional<Grading> op = super.findOne(id);
        Grading asg = op.get();
        Optional<Grading> optional = super.delete(id);
        if (!optional.isPresent()) {
            return optional;
        }
        deleteFromFile(asg);
        return Optional.empty();
    }

    @Override
    public Optional<Grading> update(Grading entity) throws ValidatorException {
        Optional<Grading> optional = super.findOne(entity.getId());
        if (!optional.isPresent()) {
            return optional;
        }
        deleteFromFile(entity);
        saveToFile(entity);
        super.update(entity);
        return Optional.empty();
    }

    private void saveToFile(Grading entity) {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write("\n" + entity.getId() + "," + entity.getAID() + "," + entity.getGrade());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFromFile(Grading gr) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path path = Paths.get(fileName);
        super.findAll().forEach(grading -> {
            if (grading.getId() != gr.getId()) {
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                    bufferedWriter.write(grading.getId() + "," + grading.getAID() + "," + grading.getGrade() + "\n" );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}