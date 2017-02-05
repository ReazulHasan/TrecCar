package edu.unh.cs.ir.eval;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class EvaluationMeasuresTest
    extends TestCase
{

    public void testFalseNegative()
    {
        EvaluationMeasures e = new EvaluationMeasures(20);
        assertEquals(e.getFalseNegatives(),20 );
    }

}
