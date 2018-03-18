package ro.ubb.LabProb.Repository.XMLRepository;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import ro.ubb.LabProb.Domain.Assign;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.InMemoryRepository;
import ro.ubb.LabProb.Repository.XMLRepository.Reader.XMLReaderAssign;
import ro.ubb.LabProb.Repository.XMLRepository.Writer.XMLWriterAssign;


public class XMLAssignRepository extends InMemoryRepository<Long, Assign> {
    private String fileName;

    public XMLAssignRepository(Validator<Assign> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData() {
        List<Assign> assigns = new XMLReaderAssign(fileName).loadEntities();
        for (Assign assign : assigns) {
            try {
                super.save(assign);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Assign> save(Assign entity) throws ValidatorException {
        Optional<Assign> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        new XMLWriterAssign(fileName).save(entity);
        return Optional.empty();
    }

    @Override
    public Optional<Assign> delete(Long ID) throws ValidatorException {
        Optional<Assign> optional = super.delete(ID);
        if (optional.isPresent()) {
            writeDataToXML();
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Assign> update(Assign entity) throws ValidatorException {
        Optional<Assign> optional = super.update(entity);
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
            pw.write("<Assigns xmlns=\"Assigns\"></Assigns>");
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.findAll()
                .forEach(student -> {
                    try {
                        new XMLWriterAssign(fileName).save(student);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}