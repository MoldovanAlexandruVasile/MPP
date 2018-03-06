package ro.ubb.LabProb.Test;

import ro.ubb.LabProb.Domain.Student;

public class TestStudent
{
    private static final Long ID = new Long(1);
    private static final Long NEW_ID = new Long(2);
    private static final String SERIAL_NUMBER = "sn01";
    private static final String NEW_SERIAL_NUMBER = "sn02";
    private static final String NAME = "studentName";
    private Student student;

    public void runTestStudent()
    {
        setUpStudent();
        testSerialNumberFunctions();
        testIDFunctions();
        testEquals();
    }

    private void setUpStudent()
    {
        student = new Student(SERIAL_NUMBER, NAME);
        student.setId(ID);
    }

    private void testSerialNumberFunctions()
    {
        assert(student.getSerialNumber().equals("sn01"));
        student.setSerialNumber(NEW_SERIAL_NUMBER);
        assert(student.getSerialNumber().equals("sn02"));
    }

    private void testIDFunctions()
    {
        assert(student.getId() == 1);
        student.setId(NEW_ID);
        assert(student.getId() == 2);
    }

    private void testEquals()
    {
        Student student2, student3;
        student2 = new Student("2172", "Alex");
        Long ID2 = new Long(1);
        student2.setId(ID2);
        assert(!student2.equals(student));

        student3 = new Student("2172", "Alex");
        Long ID3 = new Long(1);
        student3.setId(ID3);
        assert(student.equals(student));
    }

}
