package LabProb.Repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.LabProb.Domain.Student;
import ro.ubb.LabProb.Domain.Validator.StudentValidator;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Repository.InMemoryRepository;
import ro.ubb.LabProb.Repository.Repository;

import java.util.Iterator;
import java.util.Optional;
import static org.junit.Assert.assertEquals;

public class TestInMemoryRepository
{
    private Validator<Student> studentValidator = new StudentValidator();
    private Repository<Long, Student> studentRepository = new InMemoryRepository<>(studentValidator);
    private static final Long ID = new Long(1);
    private static final Long NEW_ID = new Long(2);
    private static final String SERIAL_NUMBER = "1";
    private static final String NEW_SERIAL_NUMBER = "2";
    private static final String NAME = "Name";
    private static final String NEW_NAME = "Name2";
    private Student student;

    @Before
    public void setUp() throws Exception
    {
        student = new Student(SERIAL_NUMBER, NAME);
        student.setId(ID);
        studentRepository.save(student);
    }

    @After
    public void tearDown() throws Exception
    {
        studentRepository = null;
    }

    @Test
    public void testGetSize() throws Exception
    {
        assertEquals("The size should be the same !", studentRepository.getSize(), 1);
    }

    @Test
    public void testFindOne() throws Exception
    {
        Optional<Student> op = studentRepository.findOne(student.getId());
        assertEquals("The student should be found in the repository !", student.toString(), op.get().toString());
    }

    @Test
    public void testFindAll() throws Exception
    {
        Iterable<Student> op = studentRepository.findAll();
        long size = op.spliterator().getExactSizeIfKnown();
        assertEquals("The length should be the same !", size, 1);
    }

    @Test
    public void testSave() throws Exception
    {
        Optional<Student> op = studentRepository.findOne(student.getId());
        assertEquals("The student should be found in the repository !", student.toString(), op.get().toString());
    }

    @Test
    public void testDelete() throws Exception
    {
        studentRepository.delete(ID);
        Iterable<Student> op = studentRepository.findAll();
        long size = op.spliterator().getExactSizeIfKnown();
        assertEquals("The length should be the same !", size, 0);
    }

    @Test
    public void testUpdate() throws Exception
    {
        student.setName(NEW_NAME);
        studentRepository.update(student);
        Optional<Student> op = studentRepository.findOne(student.getId());
        Student nstudent = op.get();
        assertEquals("The name should be the same !", nstudent.getName(), NEW_NAME);
    }
}
