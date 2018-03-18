package ro.ubb.LabProb.Repository.XMLRepository;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import ro.ubb.LabProb.Domain.Problem;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.InMemoryRepository;
import ro.ubb.LabProb.Repository.XMLRepository.Reader.XMLReaderProblem;
import ro.ubb.LabProb.Repository.XMLRepository.Writer.XMLWriterProblem;

public class XMLProblemRepository extends InMemoryRepository<Long, Problem> {
    private String fileName;

    public XMLProblemRepository(Validator<Problem> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData() {
        List<Problem> problems = new XMLReaderProblem(fileName).loadEntities();
        for (Problem problem : problems) {
            try {
                super.save(problem);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Problem> save(Problem entity) throws ValidatorException {
        Optional<Problem> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        new XMLWriterProblem(fileName).save(entity);
        return Optional.empty();
    }

    @Override
    public Optional<Problem> delete(Long ID) throws ValidatorException {
        Optional<Problem> optional = super.delete(ID);
        if (optional.isPresent()) {
            writeDataToXML();
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Problem> update(Problem entity) throws ValidatorException {
        Optional<Problem> optional = super.update(entity);
        if (optional.isPresent()) {
            writeDataToXML();
            return optional;
        }
        return Optional.empty();
    }

    private void writeDataToXML() {
        try {
            FileWriter fw = new FileWriter(fileName, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.write("<Problems xmlns=\"Problems\"></Problems>");
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.findAll()
                .forEach(student -> {
                    try {
                        new XMLWriterProblem(fileName).save(student);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}