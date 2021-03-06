package com.byndyusoft.calculator.domain.parser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fedor on 27.02.17.
 */
public class SimpleExpressionValidatorTest {

    @Test
    public void testNull() {
        ExpressionValidator validator = new SimpleExpressionValidator();
        boolean valid = validator.isValid(null);

        assertFalse(valid);
    }

    @Test
    public void testEmpty() {
        ExpressionValidator validator = new SimpleExpressionValidator();
        boolean valid = validator.isValid("");

        assertFalse(valid);
    }

    @Test
    public void testPairBracesFail() {
        ExpressionValidator validator = new SimpleExpressionValidator();
        boolean valid = validator.isValid("( 3 + 2");

        assertFalse(valid);
    }

    @Test
    public void testPairBracePass() {
        ExpressionValidator validator = new SimpleExpressionValidator();
        boolean valid = validator.isValid("( 3 + 2 )");

        assertTrue(valid);
    }

    @Test
    public void testAllOperatorsBinary() {
        ExpressionValidator validator = new SimpleExpressionValidator();
        boolean valid = validator.isValid("2 +");

        assertFalse(valid);
    }

}