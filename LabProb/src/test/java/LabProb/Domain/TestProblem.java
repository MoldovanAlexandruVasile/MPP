package LabProb.Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.LabProb.Domain.Problem;
import static org.junit.Assert.assertEquals;

public class TestProblem
{
    private static final Long ID = new Long(1);
    private static final Long NEW_ID = new Long(2);
    private static final String DESCR = "Description";
    private static final String NEW_DESCR = "Description2";
    private Problem problem;

    @Before
    public void setUp() throws Exception
    {
        problem = new Problem(DESCR);
        problem.setId(ID);
    }

    @After
    public void tearDown() throws Exception
    {
        problem = null;
    }

    @Test
    public void testGetDescription() throws Exception
    {
        assertEquals("The descriptions should be the same !", problem.getDescription(), DESCR);
    }

    @Test
    public void testSetDescription() throws Exception
    {
        problem.setDescription(NEW_DESCR);
        assertEquals("The descriptions should be the same !", problem.getDescription(), NEW_DESCR);
    }

    @Test
    public void testToString() throws Exception
    {
        Problem problem2 = new Problem(DESCR);
        problem2.setId(ID);
        assertEquals("The problem details should be the same !", problem.toString(), problem2.toString());
    }

    @Test
    public void testEquals() throws Exception
    {
        Problem problem2 = new Problem(DESCR);
        problem2.setId(ID);
        assertEquals("The problem details should be the same !", problem.equals(problem2), problem2.equals(problem));
    }
}
