package com.byndyusoft.calculator.domain.eval;

import com.byndyusoft.calculator.domain.eval.AbstractNode;
import com.byndyusoft.calculator.domain.eval.DivideNode;
import com.byndyusoft.calculator.domain.eval.NumberNode;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DivideNodeTest {

    @Test
    public void testEval() {
        AbstractNode node = new DivideNode(new NumberNode(5), new NumberNode(2));
        double value = node.eval();

        assertEquals(2.5, value, 0.0);
    }

    @Test
    public void testEvalInfinity() {
        AbstractNode node = new DivideNode(new NumberNode(5), null);
        double value = node.eval();

        assertEquals(Double.POSITIVE_INFINITY, value, 0.0);
    }

    @Test
    public void testEvalZero() {
        AbstractNode node = new DivideNode(null, new NumberNode(123));
        double value = node.eval();

        assertEquals(0.0, value, 0.0);
    }
}
