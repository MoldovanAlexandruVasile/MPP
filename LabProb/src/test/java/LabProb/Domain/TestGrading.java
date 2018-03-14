package LabProb.Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.LabProb.Domain.Grading;

import static org.junit.Assert.assertEquals;

public class TestGrading {
    private Grading grading;
    private static final Long ID = new Long(1);
    private static final Long NEW_ID = new Long(2);
    private static final String AID = "1";
    private static final String NEW_AID = "2";
    private static final int GRADE = 5;
    private static final int NEW_GRADE = 7;

    @Before
    public void setUp() throws Exception {
        grading = new Grading(AID, GRADE);
        grading.setId(ID);
    }

    @After
    public void tearDown() throws Exception {
        grading = null;
    }

    @Test
    public void testGetAID() throws Exception {
        assertEquals("AID's should be the same\n", grading.getAID(), AID);
    }

    @Test
    public void testGetGrade() throws Exception {
        assertEquals("Grades should be the same\n", grading.getGrade(), GRADE);
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("ID's should be the same\n", grading.getId(), ID);
    }

    @Test
    public void testSetAID() throws Exception {
        grading.setAID(NEW_AID);
        assertEquals("AID's should be the same\n", grading.getAID(), NEW_AID);
    }

    @Test
    public void testSetGrade() throws Exception {
        grading.setGrade(NEW_GRADE);
        assertEquals("Grades should be the same\n", grading.getGrade(), NEW_GRADE);
    }

    @Test
    public void testSetId() throws Exception {
        grading.setId(NEW_ID);
        assertEquals("ID's should be the same\n", grading.getId(), NEW_ID);
    }

    @Test
    public void testEquals() {
        Grading grading2 = new Grading(AID, GRADE);
        grading2.setId(ID);
        assertEquals("Gradings should be the same\n", grading, grading2);
    }

    @Test
    public void testToString() {
        Grading grading2 = new Grading(AID, GRADE);
        grading2.setId(ID);
        assertEquals("Gradings should be the same\n", grading.toString(), grading2.toString());
    }
}
