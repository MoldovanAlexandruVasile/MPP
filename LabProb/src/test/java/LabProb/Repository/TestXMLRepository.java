package LabProb.Repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.StudentValidator;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.XMLRepository.XMLStudentRepository;

import static org.junit.Assert.assertEquals;

public class TestXMLRepository {
    private Validator<Student> studentValidator = new StudentValidator();
    XMLStudentRepository xmlStudentRepository;

    @Before
    public void setUp() throws Exception {
        xmlStudentRepository = new XMLStudentRepository(studentValidator, "E:\\Programe\\IntelliJ IDEA 2017.2.5\\Projects\\MPP\\LabProb\\src\\test\\java\\LabProb\\TestFiles\\Students.xml");
    }

    @After
    public void tearDown() throws Exception {
        xmlStudentRepository = null;
    }

    @Test
    public void testLoadData() throws Exception {
        assertEquals("The length should be the same !", xmlStudentRepository.findAll().spliterator().getExactSizeIfKnown(), 4);
    }

    @Test
    public void testSave() throws Exception {
        Student std = new Student("1111", "AAAAA");
        std.setId(new Long(5));
        xmlStudentRepository.save(std);
        assertEquals("The length should be the same !", xmlStudentRepository.findAll().spliterator().getExactSizeIfKnown(), 5);
    }

    @Test
    public void testUpdate() throws Exception {
        Student std = new Student("1111", "AAAAA");
        std.setId(new Long(5));
        xmlStudentRepository.save(std);
        std.setName("BBBBBB");
        std.setSerialNumber("2222");
        xmlStudentRepository.update(std);
        assertEquals("The students should be the same", xmlStudentRepository.findOne(std.getId()).get().getSerialNumber(), "2222");
        assertEquals("The length should be the same !", xmlStudentRepository.findAll().spliterator().getExactSizeIfKnown(), 5);
    }

    @Test
    public void testDelete() throws Exception {
        xmlStudentRepository.delete(new Long(5));
        assertEquals("The length should be the same !", xmlStudentRepository.findAll().spliterator().getExactSizeIfKnown(), 4);
        assertEquals("The student should not exists anymore !", xmlStudentRepository.findOne(new Long(5)).isPresent(), false);
    }


    @Test(expected = ValidatorException.class)
    public void testSaveException() throws Exception {
        Student std = new Student("1111", "AAAAA");
        std.setId(new Long(1));
        xmlStudentRepository.save(std);
        assertEquals("The length should be the same !", xmlStudentRepository.findAll().spliterator().getExactSizeIfKnown(), 4);
    }

    @Test(expected = ValidatorException.class)
    public void testUpdateException() throws Exception {
        Student std = new Student("1111", "AAAAA");
        std.setId(new Long(6));
        xmlStudentRepository.update(std);
        assertEquals("The students should be the updated", xmlStudentRepository.findOne(std.getId()).isPresent(), true);
    }

    @Test(expected = ValidatorException.class)
    public void testDeleteException() throws Exception {
        Student std = new Student("1111", "AAAAA");
        std.setId(new Long(6));
        xmlStudentRepository.delete(std.getId());
        assertEquals("The students should be deleted", xmlStudentRepository.findOne(std.getId()).isPresent(), false);
    }
}

