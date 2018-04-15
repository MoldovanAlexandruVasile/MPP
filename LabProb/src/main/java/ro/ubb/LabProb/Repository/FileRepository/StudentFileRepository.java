package ro.ubb.LabProb.Repository.FileRepository;

import ro.ubb.socket.common.Domain.Student;
import ro.ubb.socket.common.Domain.Validator.Validator;
import ro.ubb.socket.common.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.InMemoryRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StudentFileRepository extends InMemoryRepository<Long, Student> {
    private String fileName;

    public StudentFileRepository(Validator<Student> validator, String fileName) {
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
                    String serialNumber = items.get(1);
                    String name = items.get((2));

                    Student student = new Student(serialNumber, name);
                    student.setId(id);

                    try {
                        super.save(student);
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
    public Optional<Student> save(Student entity) throws ValidatorException {
        Optional<Student> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    @Override
    public Optional<Student> delete(Long id) throws ValidatorException {
        Optional<Student> op = super.findOne(id);
        Student std = op.get();
        Optional<Student> optional = super.delete(id);
        if (!optional.isPresent()) {
            return optional;
        }
        deleteFromFile(std);
        return Optional.empty();
    }

    @Override
    public Optional<Student> update(Student entity) throws ValidatorException {
        Optional<Student> optional = super.findOne(entity.getId());
        if (!optional.isPresent()) {
            return Optional.empty();
        }
        deleteFromFile(entity);
        saveToFile(entity);
        super.update(entity);
        return optional;
    }

    private void saveToFile(Student entity) {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write("\n" + entity.getId() + "," + entity.getSerialNumber() + "," + entity.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFromFile(Student std) {
        try {
            FileWriter writer = new FileWriter(fileName, false);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path path = Paths.get(fileName);
        super.findAll().forEach(student -> {
            if (student.getId() != std.getId()) {
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                    bufferedWriter.write(student.getId() + "," + student.getSerialNumber() + "," + student.getName() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}