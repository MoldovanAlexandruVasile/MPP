package LabProb.Repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.StudentValidator;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.Repository;
import ro.ubb.LabProb.Repository.StudentFileRepository;
import java.util.Optional;
import static org.junit.Assert.assertEquals;

public class TestFileRepository {
    private Validator<Student> studentValidator = new StudentValidator();
    StudentFileRepository studentRepository = new StudentFileRepository(studentValidator, "E:\\Programe\\IntelliJ IDEA 2017.2.5\\Projects\\MPP\\LabProb\\src\\main\\java\\ro\\ubb\\LabProb\\DataFileRepository\\StudentFileRepository.txt");
    private static final Long ID = new Long(1);
    private static final Long NEW_ID = new Long(2);
    private static final String SERIAL_NUMBER = "1";
    private static final String NEW_SERIAL_NUMBER = "2";
    private static final String NAME = "Name";
    private static final String NEW_NAME = "Name2";
    private Student student;

    @Before
    public void setUp() throws Exception {
        student = new Student(SERIAL_NUMBER, NAME);
        student.setId(ID);
        studentRepository.save(student);
    }

    @After
    public void tearDown() throws Exception {
        studentRepository = null;
    }

    @Test
    public void testLoadData() throws Exception {

    }
}