package LabProb.Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.LabProb.Domain.Student;

import static org.junit.Assert.assertEquals;

public class TestStudent {
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
    }

    @After
    public void tearDown() throws Exception {
        student = null;
    }

    @Test
    public void testGetSerialNumber() throws Exception {
        assertEquals("ERROR: Serial numbers are different !", student.getSerialNumber(), SERIAL_NUMBER);
    }

    @Test
    public void testSetSerialNumber() throws Exception {
        student.setSerialNumber(NEW_SERIAL_NUMBER);
        assertEquals("ERROR: Serial numbers are different !", student.getSerialNumber(), NEW_SERIAL_NUMBER);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("ERROR: The names are different !", student.getName(), NAME);
    }

    @Test
    public void testSetName() throws Exception {
        student.setName(NEW_NAME);
        assertEquals("ERROR: The names are different !", student.getName(), NEW_NAME);
    }

    @Test
    public void testSetID() throws Exception {
        student.setId(NEW_ID);
        assertEquals("BaseEntity should be the same !", student.getId(), NEW_ID);
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals("BaseEntity should be the same !", student.getId(), ID);
    }

    @Test
    public void testEquals() throws Exception {
        Student student2 = new Student(SERIAL_NUMBER, NAME);
        student2.setId(ID);
        assertEquals("Details about those two students should be the same !", student.equals(student2), student2.equals(student));
    }

    @Test
    public void testToString() throws Exception {
        Student student2 = new Student(SERIAL_NUMBER, NAME);
        student2.setId(ID);
        assertEquals("Details about those two students should be the same !", student.toString(), student2.toString());
    }
}
