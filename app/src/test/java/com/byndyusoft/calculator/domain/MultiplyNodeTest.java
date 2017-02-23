package com.byndyusoft.calculator.domain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MultiplyNodeTest {

    @Test
    public void testEval() {
        AbstractNode node = new MultiplyNode(new NumberNode(5), new NumberNode(5));
        double value = node.eval();

        assertEquals(25.0, value, 0.0);
    }
}
