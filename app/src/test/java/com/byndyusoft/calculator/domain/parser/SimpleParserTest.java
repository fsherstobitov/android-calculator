package com.byndyusoft.calculator.domain.parser;

import com.byndyusoft.calculator.domain.eval.AbstractNode;
import com.byndyusoft.calculator.domain.eval.NumberNode;
import com.byndyusoft.calculator.domain.eval.PlusNode;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SimpleParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testParseSimpleExpr() {
        ExpressionParser parser = new SimpleParser();
        AbstractNode node = parser.parse("3 + 2");

        assertNotNull(node);
        assertTrue(node instanceof PlusNode);
        assertNotNull(node.getLeft());
        assertNotNull(node.getRight());
        assertTrue(node.getLeft() instanceof NumberNode);
        assertTrue(node.getRight() instanceof NumberNode);
        assertEquals(5.0, node.eval(), 0.0);
    }

    @Test
    public void testMultiplyExpr() {
        ExpressionParser parser = new SimpleParser();
        AbstractNode node = parser.parse("3 * 2");

        assertEquals(6.0, node.eval(), 0.0);
    }

    @Test
    public void testMinusExpr() {
        ExpressionParser parser = new SimpleParser();
        AbstractNode node = parser.parse("3 - 2");

        assertEquals(1.0, node.eval(), 0.0);
    }

    @Test
    public void testDivideExpr() {
        ExpressionParser parser = new SimpleParser();
        AbstractNode node = parser.parse("5 / 2");

        assertEquals(2.5, node.eval(), 0.0);
    }

    @Test
    public void testComplexExpr() {
        ExpressionParser parser = new SimpleParser();
        AbstractNode node = parser.parse("3 + 5 / 2");

        assertEquals(5.5, node.eval(), 0.0);
    }

    @Test
    public void testMoreComplexExpr() {
        ExpressionParser parser = new SimpleParser();
        AbstractNode node = parser.parse("3 + 5 / 2 + 8 * 4 - 3");

        assertEquals(34.5, node.eval(), 0.0);
    }
}
