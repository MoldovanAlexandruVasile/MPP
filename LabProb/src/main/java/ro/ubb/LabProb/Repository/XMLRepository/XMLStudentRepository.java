package ro.ubb.LabProb.Repository.XMLRepository;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import ro.ubb.socket.common.Domain.Student;
import ro.ubb.socket.common.Domain.Validator.Validator;
import ro.ubb.socket.common.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.InMemoryRepository;
import ro.ubb.LabProb.Repository.XMLRepository.Reader.XMLReaderStudent;
import ro.ubb.LabProb.Repository.XMLRepository.Writer.XMLWriterStudent;


public class XMLStudentRepository extends InMemoryRepository<Long, Student> {
    private String fileName;

    public XMLStudentRepository(Validator<Student> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData() {
        List<Student> students = new XMLReaderStudent(fileName).loadEntities();
        for (Student student : students) {
            try {
                super.save(student);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Student> save(Student entity) throws ValidatorException {
        Optional<Student> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        new XMLWriterStudent(fileName).save(entity);
        return Optional.empty();
    }

    @Override
    public Optional<Student> delete(Long ID) throws ValidatorException {
        Optional<Student> optional = super.delete(ID);
        if (optional.isPresent()) {
            writeDataToXML();
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Student> update(Student entity) throws ValidatorException {
        Optional<Student> optional = super.update(entity);
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
            pw.write("<Students xmlns=\"Students\"></Students>");
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.findAll()
                .forEach(student -> {
                    try {
                        new XMLWriterStudent(fileName).save(student);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}