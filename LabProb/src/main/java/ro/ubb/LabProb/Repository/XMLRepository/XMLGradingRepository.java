package ro.ubb.LabProb.Repository.XMLRepository;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import ro.ubb.socket.common.Domain.Grading;
import ro.ubb.socket.common.Domain.Validator.Validator;
import ro.ubb.socket.common.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.InMemoryRepository;
import ro.ubb.LabProb.Repository.XMLRepository.Reader.XMLReaderGrading;
import ro.ubb.LabProb.Repository.XMLRepository.Writer.XMLWriterGrading;


public class XMLGradingRepository extends InMemoryRepository<Long, Grading> {
    private String fileName;

    public XMLGradingRepository(Validator<Grading> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData() {
        List<Grading> gradings = new XMLReaderGrading(fileName).loadEntities();
        for (Grading grading : gradings) {
            try {
                super.save(grading);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Grading> save(Grading entity) throws ValidatorException {
        Optional<Grading> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        new XMLWriterGrading(fileName).save(entity);
        return Optional.empty();
    }

    @Override
    public Optional<Grading> delete(Long ID) throws ValidatorException {
        Optional<Grading> optional = super.delete(ID);
        if (optional.isPresent()) {
            writeDataToXML();
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Grading> update(Grading entity) throws ValidatorException {
        Optional<Grading> optional = super.update(entity);
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
            pw.write("<Gradings xmlns=\"Gradings\"></Gradings>");
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.findAll()
                .forEach(student -> {
                    try {
                        new XMLWriterGrading(fileName).save(student);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}