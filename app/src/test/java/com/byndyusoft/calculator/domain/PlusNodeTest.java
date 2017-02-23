package com.byndyusoft.calculator.domain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PlusNodeTest {

    @Test
    public void testEval() {
        AbstractNode node = new PlusNode(new NumberNode(3), new NumberNode(2));
        double value = node.eval();

        assertEquals(5, value, 0.0);
    }
}
