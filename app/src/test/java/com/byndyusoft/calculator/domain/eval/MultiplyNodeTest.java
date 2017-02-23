package com.byndyusoft.calculator.domain.eval;

import com.byndyusoft.calculator.domain.eval.AbstractNode;
import com.byndyusoft.calculator.domain.eval.MultiplyNode;
import com.byndyusoft.calculator.domain.eval.NumberNode;

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
