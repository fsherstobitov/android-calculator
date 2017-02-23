package com.byndyusoft.calculator.domain.eval;

import com.byndyusoft.calculator.domain.eval.AbstractNode;
import com.byndyusoft.calculator.domain.eval.MinusNode;
import com.byndyusoft.calculator.domain.eval.NumberNode;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MinusNodeTest {

    @Test
    public void testEval() {
        AbstractNode node = new MinusNode(new NumberNode(10), new NumberNode(5));
        double value = node.eval();

        assertEquals(5, value, 0.0);
    }

    @Test
    public void testEvalNegative() {
        AbstractNode node = new MinusNode(new NumberNode(3), new NumberNode(8));
        double value = node.eval();

        assertEquals(-5, value, 0.0);
    }
}
