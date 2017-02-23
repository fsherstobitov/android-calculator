package com.byndyusoft.calculator.domain.eval;

import com.byndyusoft.calculator.domain.eval.AbstractNode;
import com.byndyusoft.calculator.domain.eval.NumberNode;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class NumberNodeTest {

    @Test
    public void testEval() {
        AbstractNode node = new NumberNode(3.14);
        double value = node.eval();

        assertEquals(3.14, value, 0.0);
    }
}
