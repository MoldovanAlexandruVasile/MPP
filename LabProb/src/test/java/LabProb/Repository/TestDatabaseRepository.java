package LabProb.Repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.StudentValidator;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.DataBase.StudentDatabase;
import ro.ubb.LabProb.Repository.DataBaseRepository;

import static org.junit.Assert.assertEquals;

public class TestDatabaseRepository {
    private Validator<Student> studentValidator = new StudentValidator();
    private DataBaseRepository dataBaseRepository = new DataBaseRepository("TestStudents");
    private StudentDatabase studentDatabase;

    @Before
    public void setUp() throws Exception {
        studentDatabase = new StudentDatabase(dataBaseRepository);
    }

    @After
    public void tearDown() throws Exception {
        studentDatabase = null;
    }

    @Test
    public void testFindOne() throws Exception {
        assertEquals("The data should be the same !", studentDatabase.findOne(new Long(1)).isPresent(), true);
    }

    @Test
    public void testFindAll() throws Exception {
        assertEquals("The length should be the same !", studentDatabase.findAll().spliterator().getExactSizeIfKnown(), 4);
    }

    @Test
    public void testSave() throws Exception {
        Student student = new Student("1111", "AAAA");
        student.setId(new Long(5));
        studentDatabase.save(student);
        assertEquals("The data should be the same !", studentDatabase.findOne(new Long(1)).isPresent(), true);
        assertEquals("The length should be the same !", studentDatabase.findAll().spliterator().getExactSizeIfKnown(), 5);
    }

    @Test
    public void testUpdate() throws Exception {
        Student student = new Student("2222", "BBBB");
        student.setId(new Long(4));
        studentDatabase.update(student);
        assertEquals("The entitie should be the same !", studentDatabase.findOne(new Long(4)).get().getSerialNumber(), "2222");
    }

    @Test
    public void testDelete() throws Exception {
        studentDatabase.delete(new Long(5));
        assertEquals("The data should be the same !", studentDatabase.findOne(new Long(5)).isPresent(), false);
    }

    @Test(expected = ValidatorException.class)
    public void testSaveException() throws Exception {
        Student student = new Student("1111", "AAAA");
        student.setId(new Long(1));
        studentDatabase.save(student);
    }

    @Test
    public void testDeleteException() throws Exception {
        studentDatabase.delete(new Long(9));
    }
}