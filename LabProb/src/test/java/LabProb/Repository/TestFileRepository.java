package LabProb.Repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.StudentValidator;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.FileRepository.StudentFileRepository;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertEquals;

public class TestFileRepository {
    private Validator<Student> studentValidator = new StudentValidator();
    private StudentFileRepository studentFileRepository;

    @Before
    public void setUp() throws Exception {
        studentFileRepository = new StudentFileRepository(studentValidator, "E:\\Programe\\IntelliJ IDEA 2017.2.5\\Projects\\MPP\\LabProb\\src\\test\\java\\LabProb\\TestFiles\\StudentFileRepository.txt");
    }

    @After
    public void tearDown() throws Exception {
        studentFileRepository = null;
    }

    @Test
    public void testLoadData() throws Exception {
        assertEquals("The size should be the same !", studentFileRepository.findAll().spliterator().getExactSizeIfKnown(), 4);
    }

    @Test
    public void testSave() throws Exception {
        Student std = new Student("2190", "Mihaela");
        std.setId(new Long(5));
        studentFileRepository.save(std);
        assertEquals("The size should be the same !", studentFileRepository.findAll().spliterator().getExactSizeIfKnown(), 5);
    }

    @Test
    public void testDelete() throws Exception {
        studentFileRepository.delete(new Long(5));
        assertEquals("The size should be the same !", studentFileRepository.findOne(new Long(5)).isPresent(), false);
    }

    @Test
    public void testUpdate() throws Exception {
        Student std = new Student("1", "Ana");
        std.setId(new Long(8));
        assertEquals("The element shouldn't be present in repo !", studentFileRepository.update(std).isPresent(), false);
    }

    @Test
    public void testUpdateUndone() throws Exception {
        Student std = new Student("1111", "AAAA");
        std.setId(new Long(10));
        studentFileRepository.update(std);
        assertEquals("The element should not be updated !", studentFileRepository.findOne(std.getId()).isPresent(), false);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteException() throws Exception {
        studentFileRepository.delete(new Long(5));
        assertEquals("The size should be the same !", studentFileRepository.findOne(new Long(5)).isPresent(), false);
    }

    @Test(expected = ValidatorException.class)
    public void testSaveException() throws Exception {
        Student std = new Student("2172", "Alex");
        std.setId(new Long(1));
        studentFileRepository.save(std);
        assertEquals("The size should be the same !", studentFileRepository.findAll().spliterator().getExactSizeIfKnown(), 5);
    }
}