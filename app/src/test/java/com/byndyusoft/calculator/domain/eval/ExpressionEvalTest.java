package com.byndyusoft.calculator.domain.eval;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ExpressionEvalTest {

    /**
     * Test if (10 * (3 + 8)) expression evaluates correctly
     */
    @Test
    public void testExpressionEvaluations() {
        AbstractNode plus = new PlusNode(new NumberNode(3), new NumberNode(8));
        AbstractNode multiply = new MultiplyNode(new NumberNode(10), plus);
        double value = multiply.eval();

        assertEquals(110, value, 0.0);
    }
}
