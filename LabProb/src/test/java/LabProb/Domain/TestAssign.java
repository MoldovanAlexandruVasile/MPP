package LabProb.Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.LabProb.Domain.Assign;

import static org.junit.Assert.assertEquals;


public class TestAssign {
    private static final Long ID = new Long(1);
    private static final Long NEW_ID = new Long(2);
    private static final String SID = "1";
    private static final String NEW_SID = "2";
    private static final String PID = "3";
    private static final String NEW_PID = "4";
    private Assign assign;

    @Before
    public void setUp() throws Exception {
        assign = new Assign(SID, PID);
        assign.setId(ID);
    }

    @After
    public void tearDown() throws Exception {
        assign = null;
    }

    @Test
    public void testGetSID() throws Exception {
        assertEquals("Student ID's should be the same !", assign.getSID(), SID);
    }

    @Test
    public void testSetSID() throws Exception {
        assign.setSID(NEW_SID);
        assertEquals("Student ID's should be the same !", assign.getSID(), NEW_SID);
    }

    @Test
    public void testGetPID() throws Exception {
        assertEquals("Problem ID's should be the same !", assign.getPID(), PID);
    }

    @Test
    public void testSetPID() throws Exception {
        assign.setPID(NEW_PID);
        assertEquals("Student ID's should be the same !", assign.getPID(), NEW_PID);
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals("BaseEntity should be the same !", assign.getId(), ID);
    }

    @Test
    public void testSetID() throws Exception {
        assign.setId(NEW_ID);
        assertEquals("BaseEntity should be the same !", assign.getId(), NEW_ID);
    }

    @Test
    public void testEquals() throws Exception {
        Assign assign2 = new Assign(SID, PID);
        assign2.setId(ID);
        assertEquals("Assigns should be the same !", assign.equals(assign2), assign2.equals(assign));
    }

    @Test
    public void testToString() throws Exception {
        Assign assign2 = new Assign(SID, PID);
        assign2.setId(ID);
        assertEquals("Assigns should be the same !", assign.toString(), assign2.toString());
    }
}
