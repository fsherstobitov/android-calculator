package com.byndyusoft.calculator.domain.eval;

import com.byndyusoft.calculator.domain.eval.AbstractNode;
import com.byndyusoft.calculator.domain.eval.NumberNode;
import com.byndyusoft.calculator.domain.eval.PlusNode;

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
